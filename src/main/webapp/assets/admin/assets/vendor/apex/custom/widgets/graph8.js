var options = {
    chart: {
      height: 365,
      type: 'radialBar',
    },
    labels: ['Current Customers', 'New Customers', 'Targeted Customers'],
    series: [75, 50, 25],
    plotOptions: {
      radialBar: {
        dataLabels: {
          name: {
            show: true,
          },
          value: {
            show: true,
            formatter: function (val) {
              return val + '%'
            }
          },
          total: {
            show: true,
            label: 'Total'
          }
        }
      }
    },
    colors: ['#7943ef', '#149833', '#EB3333'],
  }
  var chart = new ApexCharts(
    document.querySelector("#graph8"),
    options
  );
  chart.render();