<%--
  Created by IntelliJ IDEA.
  User: drp
  Date: 2019/3/28
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>管理中心</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>"  rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/mycss.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/admin_mycss.css"/>" rel="stylesheet">
    <script src="<c:url value="/js/jquery-3.3.1.min.js"/>" ></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" ></script>
    <script src="<c:url value="/js/highcharts.js"/>" ></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">管理中心</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="<c:url value="/main_jsp/index.jsp"/> ">网站主页</a></li>
                <li class="active"><a href="<c:url value="/main_jsp/admin_jsp/admin_index.jsp"/>">用户数据</a></li>
                <li><a href="<c:url value="/main_jsp/admin_jsp/annunciate_write.jsp"/>">信息发布</a></li>
                <li><a href="#">资源修改</a></li>
                <li><a href="#">用户封禁</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2  sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="<c:url value="/main_jsp/admin_jsp/admin_index.jsp"/> ">流量数据<span class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="#">热门数据</a></li>
            </ul>
        </div>
        <div class="col-sm-10 col-sm-offset-2  main">
            <div  class=" panel panel-default">
                <div class="panel-heading">
                <h1>流量统计</h1>
                </div>
                <div class="panel-body">
                    <input type="hidden" id="flow">
                    <div id="container"></div>
                </div>
                    <script language="JavaScript">
                        $(document).ready(function() {

                            function getuserflow() {
                                $.get({
                                    url:"/DbStudy2_1_war_exploded/StatisitcsServlet?method=ajaxGetUserFlow",
                                    type:"post",
                                    success(data){
                                        $("#flow").val(data);
                                    }
                                });
                            }
                            var chart = {
                                type: 'spline',
                                animation: Highcharts.svg,
                                marginRight: 10,
                                events: {
                                    load: function () {
                                        var series = this.series[0];
                                        setInterval(function () {
                                            getuserflow();
                                            var x = (new Date()).getTime(), // current time
                                                y =$("#flow").val()*1.0;
                                            series.addPoint([x, y], true, true);
                                        }, 10000);
                                    }
                                }
                            };
                            var title = {
                                text: '当前网站流量统计'
                            };
                            var xAxis = {
                                type: 'datetime',
                                tickPixelInterval: 150
                            };
                            var yAxis = {
                                title: {
                                    text: '流量'
                                },
                                plotLines: [{
                                    value: 0,
                                    width: 1,
                                    color: '#808080'
                                }]
                            };
                            var tooltip = {
                                formatter: function () {
                                    return '<b>' + this.series.name + '</b><br/>' +
                                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                        Highcharts.numberFormat(this.y, 2);
                                }
                            };
                            var plotOptions = {
                                area: {
                                    pointStart: 1940,
                                    marker: {
                                        enabled: false,
                                        symbol: 'circle',
                                        radius: 2,
                                        states: {
                                            hover: {
                                                enabled: true
                                            }
                                        }
                                    }
                                }
                            };
                            var legend = {
                                enabled: false
                            };
                            var exporting = {
                                enabled: false
                            };
                            var series= [{
                                name: '流量',
                                data: (function () {
                                    var data = [],time = (new Date()).getTime(),i;
                                    for (i = -5; i <= 0; i += 1) {
                                        data.push({
                                            x: time + i * 1000,
                                            y: 0
                                        });
                                    }
                                    return data;
                                }())
                            }];

                            var json = {};
                            json.chart = chart;
                            json.title = title;
                            json.tooltip = tooltip;
                            json.xAxis = xAxis;
                            json.yAxis = yAxis;
                            json.legend = legend;
                            json.exporting = exporting;
                            json.series = series;
                            json.plotOptions = plotOptions;


                            Highcharts.setOptions({
                                global: {
                                    useUTC: false
                                }
                            });
                            $('#container').highcharts(json);
                        });
                    </script>
            </div>
        </div>
    </div>
</div>
</body>
</html>
