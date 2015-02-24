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
    $scope.charts = [];

    var commonConfig = {
      options: {
        chart: {
          zoomType: 'x'
        },
        tooltip: {
          style: {
            padding: 10,
            fontWeight: 'bold'
          }
        },
        plotOptions: {
          area: {
            fillColor: {
              linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
              stops: [
                [0, Highcharts.getOptions().colors[0]],
                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
              ]
            },
            marker: {
              radius: 2
            },
            lineWidth: 1,
            states: {
              hover: {
                lineWidth: 1
              }
            },
            threshold: null
          }
        }
      },
      //subtitle: {
      //  text: document.ontouchstart === undefined ?
      //    'Click and drag in the plot area to zoom in' :
      //    'Pinch the chart to zoom in'
      //},
      loading: false,
      xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the dummy year
          month: '%e. %b',
          year: '%b'
        },
        title: {
          text: 'Время'
        }
      },
      legend: {
        enabled: false
      }
    };

    for (var i = 0; i < window.charts.length; i++) {
      $scope.charts.push($.extend({}, commonConfig, {
        title: {
          text: window.charts[i].name
        },
        yAxis: {
          title: {
            text: window.charts[i].yLabel
          }
        },
        series: [{
          type: 'area',
          showInLegend: false,
          data: window.charts[i].data
        }]
      }));

    }
  });
