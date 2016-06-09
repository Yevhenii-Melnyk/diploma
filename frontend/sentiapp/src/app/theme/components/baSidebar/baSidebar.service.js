(function () {
    'use strict';

    angular.module('BlurAdmin.theme.components')
        .service('baSidebarService', baSidebarService);

    /** @ngInject */
    function baSidebarService($state, layoutSizes, $auth) {

        var isMenuCollapsed = shouldMenuBeCollapsed();

        var staticMenuItems = [];

        this.getMenuItems = function () {
            var states = defineMenuItemStates();
            var menuItems = states.filter(function (item) {
                return item.level == 0;
            });

            menuItems.forEach(function (item) {
                var children = states.filter(function (child) {
                    return child.level == 1 && child.name.indexOf(item.name) === 0;
                });
                item.subMenu = children.length ? children : null;
            });

            return menuItems.concat(staticMenuItems);
        };

        this.shouldMenuBeCollapsed = shouldMenuBeCollapsed;
        this.canSidebarBeHidden = canSidebarBeHidden;

        this.setMenuCollapsed = function (isCollapsed) {
            isMenuCollapsed = isCollapsed;
        };

        this.isMenuCollapsed = function () {
            return isMenuCollapsed;
        };

        this.toggleMenuCollapsed = function () {
            isMenuCollapsed = !isMenuCollapsed;
        };

        function defineMenuItemStates() {
            return $state.get()
                .filter(function (s) {
                    var meta = s.sidebarMeta;
                    if (meta && (!meta.secured || meta.secured && $auth.isAuthenticated()))
                        return meta;
                })
                .map(function (s) {
                    var meta = s.sidebarMeta;

                    return {
                        name: s.name,
                        title: s.title,
                        level: (s.name.match(/\./g) || []).length - 1,
                        order: meta.order,
                        icon: meta.icon,
                        stateRef: s.name,
                    };
                })
                .sort(function (a, b) {
                    return (a.level - b.level) * 100 + a.order - b.order;
                });
        }

        function shouldMenuBeCollapsed() {
            return window.innerWidth <= layoutSizes.resWidthCollapseSidebar;
        }

        function canSidebarBeHidden() {
            return window.innerWidth <= layoutSizes.resWidthHideSidebar;
        }
    }
})();
