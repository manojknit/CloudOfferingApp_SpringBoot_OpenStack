google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Wordpress',11],
    ['Cirross',2]
  ]);

  var options = {
    title: 'Usage',
    is3D: true,
    chartArea:{top:20,width:'30%',height:'60%'}


  };

  var chart = new google.visualization.PieChart(document.getElementById('chart_div_Pie'));
  chart.draw(data, options);
}