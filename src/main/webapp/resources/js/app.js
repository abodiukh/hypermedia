// app.js
var app = angular.module('routerApp', ['ui.router']);
var data = {};

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/home/profile");

    $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: '/resources/templates/home.html',
                controller: 'homeController'
            })
            .state('home.ads', {
                url: '/ads',
                templateUrl: '/resources/templates/ads.html',
                controller: 'adsController'
            })
            .state('home.users', {
                url: '/users',
                templateUrl: '/resources/templates/users.html',
                controller: 'usersController'
            })
            .state('home.profile', {
                url: '/profile'
            })
            .state('about', {})


});
