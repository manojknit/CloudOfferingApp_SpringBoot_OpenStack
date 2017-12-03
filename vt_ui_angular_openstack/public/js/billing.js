google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawStuff);

function drawStuff() {
  var data = new google.visualization.arrayToDataTable([
    ['Move', 'Percentage'],
    ["Aug 2017", 44],
    ["Sept 2017", 31],
    ["Oct 2017", 50],
    ["Nov 2017", 107.99],
    ['Dec 2017', 3]
  ]);

  var options = {
    width: 900,
    legend: { position: 'none' },
    chart: {
      title: 'Month to date for Nov 2017 due $107.99',
      subtitle: 'Amount in  $' },
    axes: {
      x: {
        0: { side: 'top', label: 'Months'} // Top x-axis.
      }
    },
    bar: { groupWidth: "60%" }
  };

  var chart = new google.charts.Bar(document.getElementById('chart_div_billing'));
  // Convert the Classic options to Material options.
  chart.draw(data, google.charts.Bar.convertOptions(options));
};
