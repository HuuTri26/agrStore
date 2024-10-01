var options = {
    series: [42, 47, 52, 58, 65],
    chart: {
        width: 360,
        type: 'polarArea'
    },
    labels: ['20', '30', '40', '50', '60+'],
    fill: {
        opacity: 1
    },
    stroke: {
        width: 1,
        colors: undefined
    },
    colors: ['#7943ef', '#59a2fb', '#8ec0fd', '#cce2fd', '#ebf4ff'],
    yaxis: {
        show: false
    },
    legend: {
        position: 'bottom'
    },
    tooltip: {
        y: {
            formatter: function(val) {
                return val + " Million"
            }
        }
    },
    plotOptions: {
        polarArea: {
            rings: {
                strokeWidth: 0
            },
            spokes: {
                strokeWidth: 0
            },
        }
    },
};

  var chart = new ApexCharts(document.querySelector("#demography"), options);
  chart.render();