package ua.nure.sentiment.service;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.Model;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.util.TextCleaner;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Component
public class SparkSentimentService {

    @Autowired
    private JavaSparkContext sparkContext;

    @Autowired
    private SQLContext sqlContext;

    private Model<?> logisticModel;

    public SparkSentimentService() {
        logisticModel = (Model<?>) loadModel();
    }

    @PostConstruct
    public void postConstruct() {
        registerTweetToTokensFunction(sqlContext);
    }

    public Sentiment detectSentiment(String text) {
        JavaRDD<Row> rdd = sparkContext.parallelize(Collections.singletonList(text)).map(RowFactory::create);
        DataFrame testData = sqlContext.createDataFrame(rdd, DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("text", DataTypes.StringType, true)
        }));
        testData = testData.withColumn("uniBiGram", callUDF("tweetToTokens", col("text")));
        Double prediction = (Double) logisticModel.transform(testData).select("prediction").first().get(0);

        return Sentiment.convert(prediction.intValue());
    }


    private static Object loadModel() {
        try (ObjectInputStream oos = new ObjectInputStream(SparkSentimentService.class.getClassLoader().getResourceAsStream("logistic"))) {
            return oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void registerTweetToTokensFunction(SQLContext sqlContext) {
        UDF1<String, String[]> cleanReviewFunction = (UDF1<String, String[]>) TextCleaner::cleanTweet;
        ArrayType stringArray = DataTypes.createArrayType(DataTypes.StringType);
        sqlContext.udf().register("tweetToTokens", cleanReviewFunction, stringArray);
    }

}
