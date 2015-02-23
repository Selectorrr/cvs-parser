'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('MainCtrl', function ($scope) {
    $scope.name1 = chart1.name;
    $scope.name2 = chart2.name;
    $scope.name3 = chart3.name;
    $scope.name4 = chart4.name;
    $scope.chart1 = {
      labels : chart1.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart1.temperature
        }
      ]
    };
    $scope.chart2 = {
      labels : chart1.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart1.wetness
        }
      ]
    };
    $scope.chart3 = {
      labels : chart2.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart2.temperature
        }
      ]
    };
    $scope.chart4 = {
      labels : chart2.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart2.wetness
        }
      ]
    };
    $scope.chart5 = {
      labels : chart3.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart3.temperature
        }
      ]
    };
    $scope.chart6 = {
      labels : chart3.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart3.wetness
        }
      ]
    };
    $scope.chart7 = {
      labels : chart4.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart4.temperature
        }
      ]
    };
    $scope.chart8 = {
      labels : chart4.dateTime,
      datasets : [
        {
          fillColor : "rgba(151,187,205,0)",
          strokeColor : "#e67e22",
          pointColor : "rgba(151,187,205,0)",
          pointStrokeColor : "#e67e22",
          data : chart4.wetness
        }
      ]
    };

    $scope.options = {
      responsive: true,
      maintainAspectRatio: true
    }
  });
