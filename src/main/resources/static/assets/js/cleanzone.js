/*!
 * cleanzone-template v2.0.0 (https://foxythemes.net/themes/cleanzone/)
 * Copyright 2014-2015 Foxy Themes all rights reserved 
 */
var App = function () {
    function a(a) {
        {
            var b = $("#sidebar-collapse")[0], c = $("#cl-wrapper");
            $(".cl-sidebar")
        }
        c.hasClass("sb-collapsed") ? ($(".fa", b).addClass("fa-angle-left").removeClass("fa-angle-right"), c.removeClass("sb-collapsed")) : ($(".fa", b).removeClass("fa-angle-left").addClass("fa-angle-right"), c.addClass("sb-collapsed"))
    }

    var b = {animate: !1, popover: !0, assetsPath: "assets", imgPath: "img", jsPath: "js", libsPath: "lib", tooltip: !0};
    return{conf: b, init: function (c) {
        function d() {
            var a = $("#cl-wrapper .collapse-button"), b = a.outerHeight(), c = $("#head-nav").height(), d = $(window).height() - (a.is(":visible") ? b : 0) - c;
            f.css("height", d), $("#cl-wrapper .nscroller").nanoScroller({preventPageScrolling: !0})
        }

        function e(a, b) {
            if (($("#cl-wrapper").hasClass("sb-collapsed") || $(window).width() > 755 && $(window).width() < 963) && $("ul", a).length > 0) {
                $(a).removeClass("ocult");
                var c = $("ul", a);
                if (!$(".dropdown-header", a).length) {
                    var d = '<li class="dropdown-header">' + $(a).children().html() + "</li>";
                    c.prepend(d)
                }
                g.appendTo("body");
                var e = $(a).offset().top + 8 - $(window).scrollTop(), f = $(a).width();
                g.css({top: e, left: f + 8}), g.html('<ul class="sub-menu">' + c.html() + "</ul>"), g.show(), c.css("top", e)
            } else g.hide()
        }

        if ($.extend(b, c), $(".cl-vnavigation li ul").each(function () {
            $(this).parent().addClass("parent")
        }), $(".cl-vnavigation li ul li.active").each(function () {
            $(this).parent().show().parent().addClass("open")
        }), $(".cl-vnavigation").delegate(".parent > a", "click", function (a) {
            $(".cl-vnavigation .parent.open > ul").not($(this).parent().find("ul")).slideUp(300, "swing", function () {
                $(this).parent().removeClass("open")
            });
            var b = $(this).parent().find("ul");
            b.slideToggle(300, "swing", function () {
                var a = $(this).parent();
                a.hasClass("open") ? a.removeClass("open") : a.addClass("open"), $("#cl-wrapper .nscroller").nanoScroller({preventPageScrolling: !0})
            }), a.preventDefault()
        }), $(".cl-toggle").click(function (a) {
            var b = $(".cl-vnavigation");
            b.slideToggle(300, "swing", function () {
            }), a.preventDefault()
        }), $("#sidebar-collapse").click(function () {
            a()
        }), $("#cl-wrapper").hasClass("fixed-menu")) {
            var f = $("#cl-wrapper .menu-space");
            f.addClass("nano nscroller"), $(window).resize(function () {
                d()
            }), d(), $("#cl-wrapper .nscroller").nanoScroller({preventPageScrolling: !0})
        }
        var g = $("<div id='sub-menu-nav' style='position:fixed;z-index:9999;'></div>");
        $(".cl-vnavigation li").hover(function (a) {
            e(this, a)
        }, function (a) {
            g.removeClass("over"), setTimeout(function () {
                !g.hasClass("over") && !$(".cl-vnavigation li:hover").length > 0 && g.hide()
            }, 500)
        }), g.hover(function (a) {
            $(this).addClass("over")
        }, function () {
            $(this).removeClass("over"), g.fadeOut("fast")
        }), $(document).click(function () {
            g.hide()
        }), $(document).on("touchstart click", function (a) {
            g.fadeOut("fast")
        }), g.click(function (a) {
            a.stopPropagation()
        }), $(".cl-vnavigation li").click(function (a) {
            ($("#cl-wrapper").hasClass("sb-collapsed") || $(window).width() > 755 && $(window).width() < 963) && $("ul", this).length > 0 && !($(window).width() < 755) && (e(this, a), a.stopPropagation())
        });
        var h = 220, i = 500, j = $('<a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>');
        j.appendTo("body"), jQuery(window).scroll(function () {
            jQuery(this).scrollTop() > h ? jQuery(".back-to-top").fadeIn(i) : jQuery(".back-to-top").fadeOut(i)
        }), jQuery(".back-to-top").click(function (a) {
            return a.preventDefault(), jQuery("html, body").animate({scrollTop: 0}, i), !1
        }), $(".nscroller").nanoScroller(), b.animate ? $("body").animate({opacity: 1, "margin-left": 0}, 400) : $("body").css({opacity: 1, "margin-left": 0}), $(".dropdown").on("shown.bs.dropdown", function () {
            $(".nscroller").nanoScroller()
        }), $(".nav-tabs").on("http://foxythemes.net/cleanzone/assets/js/shown.bs.tab", function (a) {
            $(".nscroller").nanoScroller()
        }), b.tooltip && $('.ttip, [data-toggle="tooltip"]').tooltip(), b.popover && $('[data-popover="popover"]').popover()
    }}
}(), App = function () {
    return App.pageCalendar = function () {
        "use strict";
        $("#external-events div.external-event").each(function () {
            var a = {title: $.trim($(this).text())};
            $(this).data("eventObject", a), $(this).draggable({zIndex: 999, revert: !0, revertDuration: 0})
        });
        var a = new Date, b = a.getDate(), c = a.getMonth(), d = a.getFullYear();
        $("#calendar").fullCalendar({header: {left: "title", center: "", right: "month,agendaWeek,agendaDay, today, prev,next"}, editable: !0, events: [
            {title: "All Day Event", start: new Date(d, c, 1)},
            {title: "Long Event", start: new Date(d, c, b - 5), end: new Date(d, c, b - 2)},
            {id: 999, title: "Repeating Event", start: new Date(d, c, b - 3, 16, 0), allDay: !1},
            {id: 999, title: "Repeating Event", start: new Date(d, c, b + 4, 16, 0), allDay: !1},
            {title: "Meeting", start: new Date(d, c, b, 10, 30), allDay: !1},
            {title: "Lunch", start: new Date(d, c, b, 12, 0), end: new Date(d, c, b, 14, 0), allDay: !1},
            {title: "Birthday Party", start: new Date(d, c, b + 1, 19, 0), end: new Date(d, c, b + 1, 22, 30), allDay: !1},
            {title: "Click for Google", start: new Date(d, c, 28), end: new Date(d, c, 29), url: "http://google.com/"}
        ], droppable: !0, drop: function (a, b) {
            var c = $(this).data("eventObject"), d = $.extend({}, c);
            d.start = a, d.allDay = b, $("#calendar").fullCalendar("renderEvent", d, !0), $("#drop-remove").is(":checked") && $(this).remove()
        }})
    }, App
}(App || {}), App = function () {
    return App.charts = function () {
        "use strict";
        function a() {
            for (e.length > 0 && (e = e.slice(1)); e.length < f;) {
                var a = e.length > 0 ? e[e.length - 1] : 50, b = a + 10 * Math.random() - 5;
                0 > b && (b = 0), b > 100 && (b = 100), e.push(b)
            }
            for (var c = [], d = 0; d < e.length; ++d)c.push([d, e[d]]);
            return c
        }

        function b(a, b, c) {
            $("<div id='tooltip'>" + c + "</div>").css({position: "absolute", display: "none", top: b + 5, left: a + 5, border: "1px solid #000", padding: "5px", color: "#fff", "border-radius": "2px", "font-size": "11px", "background-color": "#000", opacity: .8}).appendTo("body").fadeIn(200)
        }

        function c() {
            return Math.floor(31 * Math.random()) + 10
        }

        function d() {
            j.setData([a()]), j.draw(), setTimeout(d, 500)
        }

        if (jQuery.plot) {
            {
                var e = [], f = 250, g = [
                    [1, c()],
                    [2, c()],
                    [3, 2 + c()],
                    [4, 3 + c()],
                    [5, 5 + c()],
                    [6, 10 + c()],
                    [7, 15 + c()],
                    [8, 20 + c()],
                    [9, 25 + c()],
                    [10, 30 + c()],
                    [11, 35 + c()],
                    [12, 25 + c()],
                    [13, 15 + c()],
                    [14, 20 + c()],
                    [15, 45 + c()],
                    [16, 50 + c()],
                    [17, 65 + c()],
                    [18, 70 + c()],
                    [19, 85 + c()],
                    [20, 80 + c()],
                    [21, 75 + c()],
                    [22, 80 + c()],
                    [23, 75 + c()]
                ];
                [
                    [1, c() - 5],
                    [2, c() - 5],
                    [3, c() - 5],
                    [4, 6 + c()],
                    [5, 5 + c()],
                    [6, 20 + c()],
                    [7, 25 + c()],
                    [8, 36 + c()],
                    [9, 26 + c()],
                    [10, 38 + c()],
                    [11, 39 + c()],
                    [12, 50 + c()],
                    [13, 51 + c()],
                    [14, 12 + c()],
                    [15, 13 + c()],
                    [16, 14 + c()],
                    [17, 15 + c()],
                    [18, 15 + c()],
                    [19, 16 + c()],
                    [20, 17 + c()],
                    [21, 18 + c()],
                    [22, 19 + c()],
                    [23, 20 + c()],
                    [24, 21 + c()],
                    [25, 14 + c()],
                    [26, 24 + c()],
                    [27, 25 + c()],
                    [28, 26 + c()],
                    [29, 27 + c()],
                    [30, 31 + c()]
                ]
            }
            if (0 != $("#site_statistics").size()) {
                $("#site_statistics_loading").hide(), $("#site_statistics_content").show();
                var e = ($.plot($("#site_statistics"), [
                    {data: g, label: "Sales"}
                ], {series: {lines: {show: !0, lineWidth: 2, fill: !0, fillColor: {colors: [
                    {opacity: .25},
                    {opacity: .25}
                ]}}, points: {show: !0}, shadowSize: 2}, legend: {show: !1}, grid: {labelMargin: 10, axisMargin: 500, hoverable: !0, clickable: !0, tickColor: "rgba(0,0,0,0.15)", borderWidth: 0}, colors: ["#50ACFE", "#4A8CF7", "#52e136"], xaxis: {ticks: 11, tickDecimals: 0}, yaxis: {ticks: 5, tickDecimals: 0}}), $.plot($("#site_statistics2"), [
                    {data: g, label: "Unique Visits"}
                ], {series: {bars: {show: !0, barWidth: .6, lineWidth: 0, fill: !0, hoverable: !0, fillColor: {colors: [
                    {opacity: 1},
                    {opacity: 1}
                ]}}, shadowSize: 2}, legend: {show: !1}, grid: {labelMargin: 10, axisMargin: 500, hoverable: !0, clickable: !0, tickColor: "rgba(0,0,0,0.15)", borderWidth: 0}, colors: ["#FD6A5E", "#FFFFFF", "#52e136"], xaxis: {ticks: 11, tickDecimals: 0}, yaxis: {ticks: 6, tickDecimals: 0}}), [
                    {label: "Google", data: 50},
                    {label: "Dribbble", data: 7},
                    {label: "Twitter", data: 8},
                    {label: "Youtube", data: 9},
                    {label: "Microsoft", data: 14},
                    {label: "Apple", data: 13},
                    {label: "Amazon", data: 10},
                    {label: "Facebook", data: 5}
                ]);
                $.plot("#piec", e, {series: {pie: {show: !0, innerRadius: .27, shadow: {top: 5, left: 15, alpha: .3}, stroke: {width: 0}, label: {show: !0, formatter: function (a, b) {
                    return'<div style="font-size:12px;text-align:center;padding:2px;color:#333;">' + a + "</div>"
                }}, highlight: {opacity: .08}}}, grid: {hoverable: !0, clickable: !0}, colors: ["#5793f3", "#dd4d79", "#bd3b47", "#dd4444", "#fd9c35", "#fec42c", "#d4df5a", "#5578c2"], legend: {show: !1}});
                var h = [
                    [1, c()],
                    [2, c()],
                    [3, 2 + c()],
                    [4, 3 + c()],
                    [5, 5 + c()],
                    [6, 10 + c()],
                    [7, 15 + c()],
                    [8, 20 + c()],
                    [9, 25 + c()],
                    [10, 30 + c()],
                    [11, 35 + c()],
                    [12, 25 + c()],
                    [13, 15 + c()],
                    [14, 20 + c()],
                    [15, 45 + c()],
                    [16, 50 + c()],
                    [17, 65 + c()],
                    [18, 70 + c()],
                    [19, 85 + c()],
                    [20, 80 + c()],
                    [21, 75 + c()],
                    [22, 80 + c()],
                    [23, 75 + c()]
                ], i = [
                    [1, c()],
                    [2, c()],
                    [3, 10 + c()],
                    [4, 15 + c()],
                    [5, 20 + c()],
                    [6, 25 + c()],
                    [7, 30 + c()],
                    [8, 35 + c()],
                    [9, 40 + c()],
                    [10, 45 + c()],
                    [11, 50 + c()],
                    [12, 55 + c()],
                    [13, 60 + c()],
                    [14, 70 + c()],
                    [15, 75 + c()],
                    [16, 80 + c()],
                    [17, 85 + c()],
                    [18, 90 + c()],
                    [19, 95 + c()],
                    [20, 100 + c()],
                    [21, 110 + c()],
                    [22, 120 + c()],
                    [23, 130 + c()]
                ], e = ($.plot($("#chart3"), [
                    {data: i, showLabels: !0, label: "New Visitors", labelPlacement: "below", canvasRender: !0, cColor: "#FFFFFF"},
                    {data: h, showLabels: !0, label: "Old Visitors", labelPlacement: "below", canvasRender: !0, cColor: "#FFFFFF"}
                ], {series: {lines: {show: !0, lineWidth: 1, fill: !0, fillColor: {colors: [
                    {opacity: .5},
                    {opacity: .5}
                ]}}, fillColor: "rgba(0, 0, 0, 1)", points: {show: !1, fill: !0}, shadowSize: 2}, legend: {show: !0, position: "nw", backgroundColor: "green", container: $("#chart3-legend")}, grid: {show: !1, margin: 0, labelMargin: 0, axisMargin: 0, hoverable: !0, clickable: !0, tickColor: "rgba(255,255,255,1)", borderWidth: 0}, colors: ["#E3E6E8", "#1fb594"], xaxis: {autoscaleMargin: 0, ticks: 11, tickDecimals: 0}, yaxis: {autoscaleMargin: .2, ticks: 5, tickDecimals: 0}}), []), f = 200, j = $.plot($("#chart4"), [
                    {data: a(), label: "Sales"}
                ], {series: {lines: {show: !0, lineWidth: 2, fill: !0, fillColor: {colors: [
                    {opacity: .25},
                    {opacity: .25}
                ]}}, points: {show: !1}, shadowSize: 2}, legend: {show: !1}, grid: {labelMargin: 10, axisMargin: 500, hoverable: !0, clickable: !0, tickColor: "rgba(0,0,0,0.15)", borderWidth: 0}, colors: ["#B450B2", "#4A8CF7", "#52e136"], xaxis: {ticks: 11, tickDecimals: 0}, yaxis: {ticks: 5, tickDecimals: 0}});
                d();
                var k = null;
                $("#site_statistics").bind("plothover", function (a, c, d) {
                    "(" + c.x.toFixed(2) + ", " + c.y.toFixed(2) + ")";
                    if (d) {
                        if (k != d.dataIndex) {
                            k = d.dataIndex, $("#tooltip").remove();
                            var e = d.datapoint[0].toFixed(2), f = d.datapoint[1].toFixed(2);
                            b(d.pageX, d.pageY, d.series.label + " of " + e + " = " + f)
                        }
                    } else $("#tooltip").remove(), k = null
                }), $("#site_statistics2").bind("plothover", function (a, c, d) {
                    "(" + c.x.toFixed(2) + ", " + c.y.toFixed(2) + ")";
                    if (d) {
                        if (k != d.dataIndex) {
                            k = d.dataIndex, $("#tooltip").remove();
                            var e = d.datapoint[0], f = d.datapoint[1];
                            b(d.pageX, d.pageY, d.series.label + "" + e + " = " + f + "%")
                        }
                    } else $("#tooltip").remove(), k = null
                }), $("#chart3").bind("plothover", function (a, c, d) {
                    "(" + c.x.toFixed(2) + ", " + c.y.toFixed(2) + ")";
                    if (d) {
                        if (k != d.dataIndex) {
                            k = d.dataIndex, $("#tooltip").remove();
                            var e = d.datapoint[0].toFixed(2), f = d.datapoint[1].toFixed(2);
                            b(d.pageX, d.pageY, d.series.label + " of " + e + " = " + f)
                        }
                    } else $("#tooltip").remove(), k = null
                })
            }
            $(".epie-chart").easyPieChart({lineWidth: 8, animate: {duration: 600, enabled: !0}, size: 150, scaleLength: 9, onStep: function (a, b, c) {
                var d = $("canvas", this.el).height();
                $("span", this.el).html(parseInt(c) + "%").css({"line-height": d + "px"})
            }})
        }
    }, App
}(App || {}), App = function () {
    return App.codeEditor = function () {
        "use strict";
        var a = $("#code1").html();
        a = a.replace(/&lt;/g, "<"), a = a.replace(/&gt;/g, ">"), a = $.trim(a);
        var b = CodeMirror($("#console")[0], {lineNumbers: !0, theme: "ambiance", value: a, mode: "text/html"});
        setTimeout(function () {
            b.refresh()
        }, 200)
    }, App
}(App || {}), App = function () {
    return App.dashboard = function (a) {
        "use strict";
        function b(a, b, c) {
            $("<div id='tooltip'>" + c + "</div>").css({position: "absolute", display: "none", top: b + 5, left: a + 5, border: "1px solid #000", padding: "5px", color: "#fff", "border-radius": "2px", "font-size": "11px", "background-color": "#000", opacity: .8}).appendTo("body").fadeIn(200)
        }

        function c() {
            return Math.floor(31 * Math.random()) + 10
        }

        var d = {}, e = {introTour: !0};
        $.extend(d, e, a);

    }, App
}(App || {}), App = function () {
    return App.dashboard2 = function () {
        "use strict";
        $(".spk1").sparkline([2, 4, 3, 6, 7, 5, 8, 9, 4, 2, 6, 8, 8, 9, 10], {type: "bar", width: "80px", barColor: "#4A8CF7"}), $(".spk2").sparkline([4, 6, 7, 7, 4, 3, 2, 1, 4, 4, 5, 6, 5], {type: "discrete", width: "80", lineColor: "#4A8CF7", thresholdValue: 4, thresholdColor: "#ff0000"}), $(".spk4").sparkline([2, 4, 3, 6, 7, 5, 8, 9, 4, 2, 10], {type: "bar", width: "80px", height: "30px", barColor: "#EA6153"}), $(".spk5").sparkline([5, 3, 5, 6, 5, 7, 4, 8, 6, 9, 8], {type: "bar", width: "80px", height: "30px", barColor: "#4AA3DF"}), $(".spk3").sparkline([5, 6, 7, 9, 9, 5, 3, 2, 2, 4, 6, 7], {type: "line", lineColor: "#258FEC", fillColor: "#4A8CF7", spotColor: !1, width: "80px", minSpotColor: !1, maxSpotColor: !1, highlightSpotColor: "#1e7ac6", highlightLineColor: "#1e7ac6"}), $("#world-map").vectorMap({map: "world_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#38c3c1"}, hover: {"fill-opacity": .8}}, markerStyle: {initial: {r: 10}, hover: {r: 12, stroke: "rgba(255,255,255,0.8)", "stroke-width": 4}}, markers: [
            {latLng: [41.9, 12.45], name: "1.512 Visits", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [1.3, 103.8], name: "940 Visits", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [51.511214, -.119824], name: "530 Visits", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [40.714353, -74.005973], name: "340 Visits", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [-22.913395, -43.20071], name: "1.800 Visits", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}}
        ]});
        var a = [
            {label: "Google", data: 50},
            {label: "Dribbble", data: 15},
            {label: "Twitter", data: 12},
            {label: "Youtube", data: 14},
            {label: "Microsoft", data: 14}
        ];
        $.plot("#ticket-chart", a, {series: {pie: {show: !0, innerRadius: .5, shadow: {top: 5, left: 15, alpha: .3}, stroke: {width: 0}, label: {show: !1}, highlight: {opacity: .08}}}, grid: {hoverable: !0, clickable: !0}, colors: ["#5793f3", "#19B698", "#dd4444", "#fd9c35", "#fec42c", "#d4df5a", "#5578c2"], legend: {show: !1}}), $("table td .legend").each(function () {
            var a = $(this), b = a.data("color");
            a.css("background", b)
        }), $(".image-zoom").magnificPopup({type: "image", mainClass: "mfp-with-zoom", zoom: {enabled: !0, duration: 300, easing: "ease-in-out", opener: function (a) {
            var b = $(a);
            return b
        }}}), $(".md-trigger").modalEffects(), $("#summernote").summernote({height: 100, toolbar: [
            ["style", ["bold", "italic", "underline", "clear"]],
            ["fontsize", ["fontsize"]],
            ["color", ["color"]],
            ["para", ["ul", "ol", "paragraph"]],
            ["height", ["height"]]
        ]})
    }, App
}(App || {}), App = function () {
    return App.dataTables = function () {
        "use strict";
        function a(a, b) {
            var c = a.fnGetData(b), d = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
            return d += "<tr><td>Rendering engine:</td><td>" + c[1] + " " + c[4] + "</td></tr>", d += "<tr><td>Link to source:</td><td>Could provide a link here</td></tr>", d += "<tr><td>Extra info:</td><td>And any further details here (images etc)</td></tr>", d += "</table>"
        }

        $("#datatable").dataTable(), $(".dataTables_filter input").addClass("form-control").attr("placeholder", "Search"), $(".dataTables_length select").addClass("form-control");
        var b = $('<div class="btn-group"><button class="btn btn-default btn-xs" type="button">Actions</button><button data-toggle="dropdown" class="btn btn-xs btn-primary dropdown-toggle" type="button"><span class="caret"></span><span class="sr-only">Toggle Dropdown</span></button><ul role="menu" class="dropdown-menu pull-right"><li><a href="#">Edit</a></li><li><a href="#">Copy</a></li><li><a href="#">Details</a></li><li class="divider"></li><li><a href="#">Remove</a></li></ul></div>');
        $("#datatable tbody tr td:last-child").each(function () {
            $(this).html(""), b.clone().appendTo(this)
        });
        var b = $('<a class="btn btn-default btn-xs" href="#" data-original-title="Open" data-toggle="tooltip"><i class="fa fa-file"></i></a> <a class="btn btn-primary btn-xs" href="#" data-original-title="Edit" data-toggle="tooltip"><i class="fa fa-pencil"></i></a> <a class="btn btn-danger btn-xs" href="#" data-original-title="Remove" data-toggle="tooltip"><i class="fa fa-times"></i></a>');
        $("#datatable-icons tbody tr td:last-child").each(function () {
            $(this).html(""), b.clone().appendTo(this)
        });
        var c = document.createElement("th"), d = document.createElement("td");
        d.innerHTML = '<img class="toggle-details" src="' + App.conf.assetsPath + "/" + App.conf.imgPath + '/plus.png" />', d.className = "center", $("#datatable2 thead tr").each(function () {
            this.insertBefore(c, this.childNodes[0])
        }), $("#datatable2 tbody tr").each(function () {
            this.insertBefore(d.cloneNode(!0), this.childNodes[0])
        });
        var e = $("#datatable2").dataTable({aoColumnDefs: [
            {bSortable: !1, aTargets: [0]}
        ], aaSorting: [
            [1, "asc"]
        ]});
        $("#datatable2").delegate("tbody td img", "click", function () {
            var b = $(this).parents("tr")[0];
            e.fnIsOpen(b) ? (this.src = App.conf.assetsPath + "/" + App.conf.imgPath + "/plus.png", e.fnClose(b)) : (this.src = App.conf.assetsPath + "/" + App.conf.imgPath + "/minus.png", e.fnOpen(b, a(e, b), "details"))
        }), $(".dataTables_filter input").addClass("form-control").attr("placeholder", "Search"), $(".dataTables_length select").addClass("form-control"), $("#datatable-icons").dataTable()
    }, App
}(App || {}), App = function () {
    return App.emailInbox = function () {
        "use strict";
        $("input").iCheck({checkboxClass: "icheckbox_square-blue checkbox", radioClass: "icheckbox_square-blue"}), $("#check-all").on("ifChanged", function () {
            var a = $(".mails").find(":checkbox");
            a.iCheck($(this).is(":checked") ? "check" : "uncheck")
        })
    }, App
}(App || {}), App = function () {
    return App.formElements = function () {
        "use strict";
        $(".switch").bootstrapSwitch(), $(".datetime").datetimepicker({autoclose: !0}), $(".select2").select2({width: "100%"}), $(".tags").select2({tags: 0, width: "100%"}), jQuery().iCheck && $(".icheck").iCheck({checkboxClass: "icheckbox_square-blue checkbox", radioClass: "iradio_square-blue"}), $("#reservation").daterangepicker(), $("#reservationtime").daterangepicker({timePicker: !0, timePickerIncrement: 30, format: "MM/DD/YYYY h:mm A"});
        {
            var a = function (a, b) {
                $("#reportrange span").html(a.format("MMMM D, YYYY") + " - " + b.format("MMMM D, YYYY")), alert("Callback has fired: [" + a.format("MMMM D, YYYY") + " to " + b.format("MMMM D, YYYY") + "]")
            }, b = {startDate: moment().subtract("days", 29), endDate: moment(), minDate: "01/01/2012", maxDate: "12/31/2014", dateLimit: {days: 60}, showDropdowns: !0, showWeekNumbers: !0, timePicker: !1, timePickerIncrement: 1, timePicker12Hour: !0, ranges: {Today: [moment(), moment()], Yesterday: [moment().subtract("days", 1), moment().subtract("days", 1)], "Last 7 Days": [moment().subtract("days", 6), moment()], "Last 30 Days": [moment().subtract("days", 29), moment()], "This Month": [moment().startOf("month"), moment().endOf("month")], "Last Month": [moment().subtract("month", 1).startOf("month"), moment().subtract("month", 1).endOf("month")]}, opens: "left", buttonClasses: ["btn"], applyClass: "btn-small btn-primary", cancelClass: "btn-small", format: "MM/DD/YYYY", separator: " to ", locale: {applyLabel: "Submit", cancelLabel: "Clear", fromLabel: "From", toLabel: "To", customRangeLabel: "Custom", daysOfWeek: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"], monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"], firstDay: 1}};
            ({startDate: moment().subtract("days", 7), endDate: moment(), opens: "left", ranges: {Today: [moment(), moment()], Yesterday: [moment().subtract("days", 1), moment().subtract("days", 1)], "Last 7 Days": [moment().subtract("days", 6), moment()], "Last 30 Days": [moment().subtract("days", 29), moment()], "This Month": [moment().startOf("month"), moment().endOf("month")], "Last Month": [moment().subtract("month", 1).startOf("month"), moment().subtract("month", 1).endOf("month")]}})
        }
        $("#reportrange span").html(moment().subtract("days", 29).format("MMMM D, YYYY") + " - " + moment().format("MMMM D, YYYY")), $("#reportrange").daterangepicker(b, a), $(".bslider").bootstrapSlider()
    }, App
}(App || {}), App = function () {
    return App.masks = function () {
        "use strict";
        $("[data-mask='date']").mask("99/99/9999"), $("[data-mask='phone']").mask("(999) 999-9999"), $("[data-mask='phone-ext']").mask("(999) 999-9999? x99999"), $("[data-mask='phone-int']").mask("+33 999 999 999"), $("[data-mask='taxid']").mask("99-9999999"), $("[data-mask='ssn']").mask("999-99-9999"), $("[data-mask='product-key']").mask("a*-999-a999"), $("[data-mask='percent']").mask("99%"), $("[data-mask='currency']").mask("http://foxythemes.net/cleanzone/assets/js/$999,999,999.99")
    }, App
}(App || {}), App = function () {
    return App.multiSelect = function () {
        "use strict";
        $("#example1").multiselect(), $("#example2").multiselect(), $("#example3").multiselect({buttonClass: "btn btn-link"}), $("#example4").multiselect({buttonClass: "btn btn-default btn-sm"}), $("#example6").multiselect(), $("#example9").multiselect({onChange: function (a, b) {
            alert("Change event invoked!"), console.log(a)
        }});
        for (var a = 1; 100 >= a; a++)$("#example11").append('<option value="' + a + '">Options ' + a + "</option>");
        $("#example11").multiselect({maxHeight: 200}), $("#example13").multiselect(), $("#example14").multiselect({buttonWidth: "500px", buttonText: function (a) {
            if (0 === a.length)return'None selected <b class="caret"></b>';
            var b = "";
            return a.each(function () {
                b += $(this).text() + ", "
            }), b.substr(0, b.length - 2) + ' <b class="caret"></b>'
        }}), $("#example16").multiselect({onChange: function (a, b) {
            b === !1 && $("#example16").multiselect("select", a.val())
        }}), $("#example19").multiselect(), $("#example20").multiselect({selectedClass: null}), $("#example23").multiselect(), $("#example24").multiselect(), $("#example25").multiselect({dropRight: !0, buttonWidth: "300px"}), $("#example27").multiselect({includeSelectAllOption: !0});
        for (var a = 1; 100 >= a; a++)$("#example28").append('<option value="' + a + '">' + a + "</option>");
        $("#example28").multiselect({includeSelectAllOption: !0, enableFiltering: !0, maxHeight: 150}), $("#example32").multiselect(), $("#example39").multiselect({includeSelectAllOption: !0, enableCaseInsensitiveFiltering: !0}), $("#example41").multiselect({includeSelectAllOption: !0}), $("#my-select").multiSelect(), $("#pre-selected-options").multiSelect(), $("#callbacks").multiSelect({afterSelect: function (a) {
            alert("Select value: " + a)
        }, afterDeselect: function (a) {
            alert("Deselect value: " + a)
        }}), $("#optgroup").multiSelect({selectableOptgroup: !0}), $("#disabled-attribute").multiSelect(), $("#custom-headers").multiSelect({selectableHeader: "<div class='custom-header'>Selectable items</div>", selectionHeader: "<div class='custom-header'>Selection items</div>"}), $("#searchable").multiSelect({selectableHeader: "<input type='text' class='form-control search-input' autocomplete='off' placeholder='Search'>", selectionHeader: "<input type='text' class='form-control search-input' autocomplete='off' placeholder='Search'>", afterInit: function (a) {
            var b = this, c = b.$selectableUl.prev(), d = b.$selectionUl.prev(), e = "#" + b.$container.attr("id") + " .ms-elem-selectable:not(.ms-selected)", f = "#" + b.$container.attr("id") + " .ms-elem-selection.ms-selected";
            b.qs1 = c.quicksearch(e).on("keydown", function (a) {
                return 40 === a.which ? (b.$selectableUl.focus(), !1) : void 0
            }), b.qs2 = d.quicksearch(f).on("keydown", function (a) {
                return 40 == a.which ? (b.$selectionUl.focus(), !1) : void 0
            })
        }, afterSelect: function () {
            this.qs1.cache(), this.qs2.cache()
        }, afterDeselect: function () {
            this.qs1.cache(), this.qs2.cache()
        }})
    }, App
}(App || {}), App = function () {
    return App.wizard = function () {
        "use strict";
        $(".wizard-ux").wizard(), $(".wizard-ux").on("changed.fu.wizard", function () {
            $(".bslider").slider()
        }), $(".wizard-next").click(function (a) {
            var b = $(this).data("wizard");
            $(b).wizard("next"), a.preventDefault()
        }), $(".wizard-previous").click(function (a) {
            var b = $(this).data("wizard");
            $(b).wizard("previous"), a.preventDefault()
        }), $(".switch").bootstrapSwitch(), $(".select2").select2({width: "100%"}), $(".tags").select2({tags: 0, width: "100%"})
    }, App
}(App || {}), App = function () {
    return App.textEditor = function () {
        "use strict";
        $("textarea.ckeditor").ckeditor(), CKEDITOR.disableAutoInline = !0, $(".inline-editable").each(function () {
            CKEDITOR.inline($(this)[0])
        }), $("#some-textarea").wysihtml5(), $("#summernote").summernote({height: 200})
    }, App
}(App || {}), App = function () {
    return App.pageGallery = function () {
        "use strict";
        var a = $(".gallery-cont");
        a.masonry({columnWidth: 0, itemSelector: ".item"}), $("#sidebar-collapse").click(function () {
            a.masonry()
        }), $(".image-zoom").magnificPopup({type: "image", mainClass: "mfp-with-zoom", zoom: {enabled: !0, duration: 300, easing: "ease-in-out", opener: function (a) {
            var b = $(a).parents("http://foxythemes.net/cleanzone/assets/js/div.img");
            return b
        }}})
    }, App
}(App || {}), App = function () {
    return App.mapsGoogle = function () {
        "use strict";
        var a, b = {zoom: 14, center: new google.maps.LatLng(-33.877827, 151.188598), mapTypeId: google.maps.MapTypeId.ROADMAP};
        a = new google.maps.Map(document.getElementById("map_one"), b);
        var c, b = {zoom: 14, center: new google.maps.LatLng(-33.877827, 151.188598), mapTypeId: google.maps.MapTypeId.HYBRID};
        c = new google.maps.Map(document.getElementById("map_two"), b);
        var d, b = {zoom: 14, center: new google.maps.LatLng(-33.877827, 151.188598), mapTypeId: google.maps.MapTypeId.TERRAIN};
        d = new google.maps.Map(document.getElementById("map_three"), b)
    }, App
}(App || {}), App = function () {
    return App.pageProfile = function () {
        "use strict";
        $(".spk4").sparkline([2, 4, 3, 6, 7, 5, 8, 9, 4, 2, 10], {type: "bar", width: "200px", barWidth: 6, height: "50px", barColor: "#19B698"}), $(".spk5").sparkline([5, 3, 5, 6, 5, 7, 4, 8, 6, 9, 8], {type: "bar", width: "80px", barWidth: 6, height: "50px", barColor: "#E85647"});
        var a, b, c, d = App.conf.assetsPath + "/" + App.conf.libsPath + "/jquery.upload/server/php/index.php";
        $("#fileupload").fileupload({url: d, dataType: "json", done: function (d, e) {
            $.each(e.result.files, function (d, e) {
                b = e.name, c = e.url, $(".crop-image").html('<img src="' + App.conf.assetsPath + "/" + App.conf.libsPath + "/jquery.upload/server/php/files/" + e.name + '" />'), $("#progress").fadeOut(), $(".crop-image img").Jcrop({aspectRatio: 1}, function () {
                    a = this
                })
            }), $("#crop-image").niftyModal()
        }, progressall: function (a, b) {
            var c = parseInt(b.loaded / b.total * 100, 10);
            $("#progress").fadeIn(), $("#progress").css("width", c + "%")
        }}).prop("disabled", !$.support.fileInput).parent().addClass($.support.fileInput ? void 0 : "disabled"), $("#save-image").click(function () {
            var b = a.tellSelect();
            0 != b.w ? ($(".profile-avatar").attr("src", App.conf.assetsPath + "/" + App.conf.libsPath + "/jquery.crop/server/crop.php?src=" + c + "&x=" + b.x + "&y=" + b.y + "&w=" + b.w + "&h=" + b.h), $("#crop-image").niftyModal("hide")) : alert("Please select a crop region then press save.")
        })
    }, App
}(App || {}), App = function () {
    return App.pageSearch = function () {
        "use strict";
        $(".datetime").datetimepicker({autoclose: !0}), $(".select2").select2({width: "100%"}), $(".tags").select2({tags: 0, width: "100%"}), $("input").iCheck({checkboxClass: "icheckbox_square-blue checkbox", radioClass: "iradio_square-blue"}), $("#check-all").on("ifChanged", function () {
            var a = $(".items").find(":checkbox");
            a.iCheck($(this).is(":checked") ? "check" : "uncheck")
        }), $("#price-range").slider().on("slide", function (a) {
            $("#price1").html("$" + a.value[0]), $("#price2").html("$" + a.value[1])
        })
    }, App
}(App || {}), App = function () {
    return App.pageSignUp = function () {
        "use strict";
        $("form").parsley()
    }, App
}(App || {}), App = function () {
    return App.nestableLists = function () {
        "use strict";
        function a(a, b) {
            var c = $(a).nestable("serialize");
            $(b).html(window.JSON.stringify(c))
        }

        $(".dd").nestable(),
            a("#list1", "#out1"), a("#list2", "#out2"), $("#list1").on("change", function () {
            a("#list1", "#out1")
        }), $("#list2").on("change", function () {
            a("#list2", "#out2")
        })
    }, App
}(App || {}), App = function () {
    return App.uiNotifications = function () {
        "use strict";
        $("#not-basic").click(function () {
            return $.gritter.add({title: "Thomas new msg!", text: "You have a new Thomas message, let's checkout your inbox.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/avatar1_50.jpg", time: ""}), !1
        }), $("#not-theme").click(function () {
            return $.gritter.add({title: "Welcome home!", text: "You can start your day checking the new messages.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/clipboard_icon.png", class_name: "clean", time: ""}), !1
        }), $("#not-sticky").click(function () {
            return $.gritter.add({title: "Sticky Note", text: "Your daily goal is 130 new code lines, don't forget to update your work.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/blueprint_icon.png", class_name: "clean", sticky: !0, time: ""}), !1
        }), $("#not-text").click(function () {
            return $.gritter.add({title: "Just Text", text: "This is a simple Gritter Notification.", class_name: "clean", time: ""}), !1
        }), $("#not-tr").click(function () {
            return $.extend($.gritter.options, {position: "top-right"}), $.gritter.add({title: "Top Right", text: "This is a simple Gritter Notification.", class_name: "clean"}), !1
        }), $("#not-tl").click(function () {
            return $.extend($.gritter.options, {position: "top-left"}), $.gritter.add({title: "Top Left", text: "This is a simple Gritter Notification.", class_name: "clean"}), !1
        }), $("#not-bl").click(function () {
            return $.extend($.gritter.options, {position: "bottom-left"}), $.gritter.add({title: "Bottom Left", text: "This is a simple Gritter Notification.", class_name: "clean"}), !1
        }), $("#not-br").click(function () {
            return $.extend($.gritter.options, {position: "bottom-right"}), $.gritter.add({title: "Bottom Right", text: "This is a simple Gritter Notification.", class_name: "clean"}), !1
        }), $("#not-facebook").click(function () {
            return $.gritter.add({title: "You have comments!", text: "You can start your day checking the new messages.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/fb-icon.png", class_name: "facebook"}), !1
        }), $("#not-twitter").click(function () {
            return $.gritter.add({title: "You have new followers!", text: "You can start your day checking the new messages.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/tw-icon.png", class_name: "twitter"}), !1
        }), $("#not-google-plus").click(function () {
            return $.gritter.add({title: "You have new +1!", text: "You can start your day checking the new messages.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/gp-icon.png", class_name: "google-plus"}), !1
        }), $("#not-dribbble").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/db-icon.png", class_name: "dribbble"}), !1
        }), $("#not-flickr").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/fl-icon.png", class_name: "flickr"}), !1
        }), $("#not-linkedin").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/in-icon.png", class_name: "linkedin"}), !1
        }), $("#not-youtube").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/yt-icon.png", class_name: "youtube"}), !1
        }), $("#not-pinterest").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/pi-icon.png", class_name: "pinterest"}), !1
        }), $("#not-github").click(function () {
            return $.gritter.add({title: "You have new forks!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/gh-icon.png", class_name: "github"}), !1
        }), $("#not-tumblr").click(function () {
            return $.gritter.add({title: "You have new comments!", text: "You can start your day checking the new comments.", image: App.conf.assetsPath + "/" + App.conf.imgPath + "/tu-icon.png", class_name: "tumblr"}), !1
        }), $("#not-primary").click(function () {
            $.gritter.add({title: "Primary", text: "This is a simple Gritter Notification.", class_name: "primary"})
        }), $("#not-success").click(function () {
            $.gritter.add({title: "Success", text: "This is a simple Gritter Notification.", class_name: "success"})
        }), $("#not-info").click(function () {
            $.gritter.add({title: "Info", text: "This is a simple Gritter Notification.", class_name: "info"})
        }), $("#not-warning").click(function () {
            $.gritter.add({title: "Warning", text: "This is a simple Gritter Notification.", class_name: "warning"})
        }), $("#not-danger").click(function () {
            $.gritter.add({title: "Danger", text: "This is a simple Gritter Notification.", class_name: "danger"})
        }), $("#not-dark").click(function () {
            $.gritter.add({title: "Dark", text: "This is a simple Gritter Notification.", class_name: "dark"})
        })
    }, App
}(App || {}), App = function () {
    return App.treeView = function () {
        "use strict";
        $("label.tree-toggler").click(function () {
            var a = $(this).children(".fa");
            a.hasClass("fa-folder-o") ? a.removeClass("fa-folder-o").addClass("fa-folder-open-o") : a.removeClass("fa-folder-open-o").addClass("fa-folder-o"), $(this).parent().children("http://foxythemes.net/cleanzone/assets/js/ul.tree").toggle(300, function () {
                $(this).parent().toggleClass("open"), $(".tree .nscroller").nanoScroller({preventPageScrolling: !0})
            })
        })
    }, App
}(App || {}), App = function () {
    return App.mapsVector = function () {
        "use strict";
        $("#world-map").vectorMap({map: "world_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "rgba(255,255,255,0.8)", "fill-opacity": 1, stroke: "none", "stroke-width": 0, "stroke-opacity": 1}, hover: {"fill-opacity": .8}}, markerStyle: {initial: {r: 10}, hover: {r: 12, stroke: "rgba(255,255,255,0.8)", "stroke-width": 4}}, markers: [
            {latLng: [41.9, 12.45], name: "1.512 Sales", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [1.3, 103.8], name: "940 Sales", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [51.511214, -.119824], name: "3.500 Sales", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [40.714353, -74.005973], name: "4.800 Sales", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}},
            {latLng: [-22.913395, -43.20071], name: "9.800 Sales", style: {fill: "#E44C34", stroke: "rgba(255,255,255,0.7)", "stroke-width": 3}}
        ]}), $("#usa-map").vectorMap({map: "us_merc_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#51ADFE"}, hover: {"fill-opacity": .8}}}), $("#france-map").vectorMap({map: "fr_merc_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#38c3c1"}, hover: {"fill-opacity": .8}}}), $("#uk-map").vectorMap({map: "uk_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#FD6A5E"}, hover: {"fill-opacity": .8}}}), $("#chicago-map").vectorMap({map: "us-il-chicago_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#d9dade"}, hover: {"fill-opacity": .8}}}), $("#australia-map").vectorMap({map: "au_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#dc6ea5"}, hover: {"fill-opacity": .8}}}), $("#india-map").vectorMap({map: "in_mill_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#ffba3b"}, hover: {"fill-opacity": .8}}}), $("#vector-map").vectorMap({map: "map", backgroundColor: "transparent", regionStyle: {initial: {fill: "#D9DADE", "fill-opacity": .8, stroke: "none", "stroke-width": 0, "stroke-opacity": 1}, hover: {"fill-opacity": .8}}, markerStyle: {initial: {r: 10}}, markers: [
            {coords: [100, 100], name: "Marker 1", style: {fill: "#acb1b6", stroke: "#cfd5db", "stroke-width": 3}},
            {coords: [200, 200], name: "Marker 2", style: {fill: "#acb1b6", stroke: "#cfd5db", "stroke-width": 3}}
        ]}), $("#canada-map").vectorMap({map: "ca_lcc_en", backgroundColor: "transparent", regionStyle: {initial: {fill: "#6f6bc8"}, hover: {"fill-opacity": .8}}})
    }, App
}(App || {});
$(function () {
    function a(a) {
        for (var b = window.location.search.substring(1), c = b.split("&"), d = 0; d < c.length; d++) {
            var e = c[d].split("=");
            if (e[0] == a)return e[1]
        }
    }

    if ("webkitSpeechRecognition"in window) {
        App.speech({lang: "en"}), App.speechCommand("go to", {action: function () {
            $.gritter.add({title: "Go to Page", text: "Tell me where do you want to go?", image: "images/mic-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/mic-icon.png*/, class_name: "clean", time: ""})
        }, listen: function (a) {
            switch (a) {
                case"dashboard":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/index.html?listen=on";
                    break;
                case"sidebar":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/layouts-sidebar.html?listen=on";
                    break;
                case"ui elements":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-elements.html?listen=on";
                    break;
                case"buttons":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-buttons.html?listen=on";
                    break;
                case"icons":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-icons.html?listen=on";
                    break;
                case"grid":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-grid.html?listen=on";
                    break;
                case"tabs":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"accordions":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"tabs and accordions":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"nestable lists":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-nestable-lists.html?listen=on";
                    break;
                case"form elements":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-elements.html?listen=on";
                    break;
                case"validation":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-validation.html?listen=on";
                    break;
                case"wizard":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-wizard.html?listen=on";
                    break;
                case"input masks":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-masks.html?listen=on";
                    break;
                case"text editor":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-wysiwyg.html?listen=on";
                    break;
                case"tables":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/tables-general.html?listen=on";
                    break;
                case"data tables":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/tables-datatables.html?listen=on";
                    break;
                case"maps":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/maps.html?listen=on";
                    break;
                case"typography":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/typography.html?listen=on";
                    break;
                case"charts":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/charts.html?listen=on";
                    break;
                case"blank page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-blank.html?listen=on";
                    break;
                case"blank page header":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-blank-header.html?listen=on";
                    break;
                case"login":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-login.html?listen=on";
                    break;
                case"404 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-404.html?listen=on";
                    break;
                case"500 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-500.html?listen=on";
                    break;
                case"500 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-500.html?listen=on";
                    break;
                default:
                    $.gritter.add({title: "Error", text: "Could not find: <strong>" + a + "</strong> page, Please try again.", image: "images/mic-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/mic-icon.png*/, class_name: "clean", time: ""})
            }
        }}), App.speechCommand("change title", {action: function () {
            $.gritter.add({title: "Change Title", text: "Tell me the new title...", image: "images/mic-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/mic-icon.png*/, class_name: "clean", time: ""})
        }, listen: function (a) {
            $(".navbar-brand span").html(a)
        }, interim: function (a) {
            $(".navbar-brand span").html(a)
        }}), App.speechCommand("log out", {action: function () {
            location.href = "http://foxythemes.net/cleanzone/assets/js/pages-login.html"
        }}), App.speechCommand("toggle sidebar", {action: function () {
            App.toggleSideBar()
        }}), App.speechCommand("scroll down", {action: function () {
            var a = $(window).scrollTop();
            $("html, body").animate({scrollTop: a + 500}, "slow")
        }}), App.speechCommand("scroll up", {action: function () {
            var a = $(window).scrollTop();
            $("html, body").animate({scrollTop: a - 500}, "slow")
        }}), App.speechCommand("go bottom", {action: function () {
            $("html, body").animate({scrollTop: $(document).height()}, "slow")
        }}), App.speechCommand("go top", {action: function () {
            $("html, body").animate({scrollTop: 0}, "slow")
        }});
        var b = {action: function () {
            $.gritter.add({title: "Hello", text: "Tell me what is your name?", image: "images/user-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/user-icon.png*/, class_name: "clean", time: ""})
        }, listen: function (a) {
            function b(a) {
                return a.replace(/\w\S*/g, function (a) {
                    return a.charAt(0).toUpperCase() + a.substr(1).toLowerCase()
                })
            }

            var c = b(a);
            $.gritter.add({title: "Clean Zone", text: "Welcome home <strong>" + c + "</strong>!", image: "images/user-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/user-icon.png*/, class_name: "clean", time: ""}), $(".side-user .info a").html(c), $(".profile_menu > a span").html(c)
        }};
        App.speechCommand("hello", b), App.speechCommand("hi", b);
        var c = {action: function () {
            $.gritter.add({title: "Clean Zone", text: "Your welcome!", image: "images/user-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/user-icon.png*/, class_name: "clean", time: ""})
        }};
        App.speechCommand("thanks", c), App.speechCommand("thank you", c), App.speechCommand("email", {dictation: !0, dictationEndCommand: "end of email", dictationEnd: function () {
            var a = $('<div class="progress progress-striped active" style="display:none;"><div style="width: 0%" class="progress-bar progress-bar-info">0%</div></div>').css("margin", "10px 0 0 0");
            $("#mod-info .interim").fadeOut(function () {
                $(this).html("")
            }), a.appendTo("#mod-info .modal-body").fadeIn(), a.find(".progress-bar").animate({width: 900}, {duration: 5e3, step: function (a, b) {
                var c = (100 * a / b.end).toFixed(0);
                $(this).html(c + "%")
            }, complete: function () {
                $("#mod-info").modal("hide")
            }}), $("#mod-info .modal-body h4").html("Thank you!"), $("#mod-info .modal-body p").addClass("text-center").html("We are sending a new e-mail...")
        }, action: function () {
            var a = $('<div role="dialog" tabindex="-1" id="mod-info" class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button></div><div class="modal-body"><div class="text-center"><div class="i-circle primary"><i class="fa fa-envelope"></i></div><h4>Tell me your message...</h4><div contenteditable="true"><p class="text-left"><span class="msg"></span><span class="interim color-primary"></span></p></div></div></div><div class="modal-footer"><button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button><button data-dismiss="modal" class="btn btn-primary" type="button">Send</button></div></div></div></div>');
            a.appendTo("body"), $("#mod-info").modal(), $("#mod-info").on("hidden.bs.modal", function () {
                $(this).removeData("bs.modal"), $(this).remove()
            })
        }, listen: function (a) {
            $("#mod-info .msg").append(" " + a), $("#mod-info .interim").fadeOut(function () {
                $(this).html("")
            })
        }, interim: function (a) {
            $("#mod-info .interim").show().html(a)
        }}), App.speechCommand("stop", {action: function () {
            App.speech("stop")
        }}), "on" == a("listen") && App.speech("start"), $(".speech-button").click(function (a) {
            var b = $('<div role="dialog" tabindex="-1" id="mod-sound" data-backdrop="false" class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button><h2 class="hthin"><img src="images/mic-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/images/mic-icon.png*/ /> Speech API</h2></div><div class="modal-body" style="padding:0 25px;"><div><h4>Voice Recognition</h4><div><p class="text-left">Thanks to Google Speech API we can do <a href="https://dvcs.w3.org/hg/speech-api/raw-file/tip/speechapi.html">Speech Recognition</a> in our web sites, initially Chrome 25+ and up versions support this, but don&#39;t worry! browsers are working on a <a href="https://wiki.mozilla.org/HTML5_Speech_API">standard</a> and soon we will see this working on our favorites browsers. </p><h4 class="spacer2">Let the party begin</h4><p>First you must to allow microphone access clicking on <strong>"Allow"</strong> option above this modal. After this you&#39;ll see the Microphone icon with a blur effect, this means that voice recognition starts.</p><h4 class="spacer2">Voice Commands</h4><p>After that, try to say <strong>"Hello"</strong> at your mic. Do you see what happens? things in template start to change, now try these commands:</p><ul><li><strong>"Go to"</strong>: wait for a message and then say a page title like "tables"</li><li><strong>"Change title"</strong> - Sets template title</li><li><strong>"Scroll down"</strong> and <strong>"Scroll up"</strong></li><li><strong>"Go top"</strong> and <strong>"Go bottom"</strong></li><li><strong>"Toggle sidebar"</strong></li><li><strong>"log out"</strong></li><li><strong>"Thank you"</strong></li><li><strong>"Stop"</strong> - Stops recognition</li><li><strong>"Email"</strong> - Compose an e-mail with your voice, to end dictation just say "end of email"</li></ul><p>Do you want more commands? you can add a voice command easily with our own API.</p></div></div></div><div class="modal-footer"><button data-dismiss="modal" class="btn btn-primary" type="button">DONE!</button></div></div></div></div>');
            b.appendTo("body"), $("#mod-sound").modal(), $("#mod-sound").on("hidden.bs.modal", function () {
                $(this).removeData("bs.modal"), $(this).remove()
            }), App.speech("start"), a.preventDefault(), a.stopPropagation()
        })
    }
});
var App = function () {
    return App.speech_commands = [], "webkitSpeechRecognition"in window && (App.rec = new webkitSpeechRecognition), App.speech = function (a) {
        "use strict";
        if ("webkitSpeechRecognition"in window)if ("start" == a)App.rec.start(); else if ("stop" == a)App.rec.stop(); else {
            var b = {continuous: !0, interim: !0, lang: !1, onEnd: !1, onResult: !1, onNoMatch: !1, onSpeechStart: !1, onSpeechEnd: !1};
            $.extend(b, a), b.continuous && (App.rec.continuous = !0), b.interim && (App.rec.interimResults = !0), b.lang && (App.rec.lang = b.lang);
            var c = !1, d = "";
            App.rec.onresult = function (a) {
                for (var e = a.resultIndex; e < a.results.length; ++e)if (a.results[e].isFinal) {
                    var f = a.results[e][0].transcript;
                    f = f.toLowerCase(), f = f.replace(/^\s+|\s+$/g, ""), console.log(f), b.onResult && b.onResult(f), $.each(App.speech_commands, function (a, b) {
                        c ? d == b.command && (b.dictation ? f == b.dictationEndCommand ? (c = !1, b.dictationEnd(f)) : b.listen(f) : (c = !1, b.listen(f))) : b.command == f && (b.action(f), b.listen && (c = !0, d = b.command))
                    })
                } else {
                    var g = a.results[e][0].transcript;
                    $.each(App.speech_commands, function (a, b) {
                        b.interim !== !1 && c && d == b.command && b.interim(g)
                    })
                }
            }, b.onNoMatch && (App.rec.onnomatch = function () {
                b.onNoMatch()
            }), b.onSpeechStart && (App.rec.onspeechstart = function () {
                b.onSpeechStart()
            }), b.onSpeechEnd && (App.rec.onspeechend = function () {
                b.onSpeechEnd()
            }), App.rec.onaudiostart = function () {
                $(".speech-button i").addClass("blur")
            }, App.rec.onend = function () {
                alert("hey"), $(".speech-button i").removeClass("blur"), b.onEnd && b.onEnd()
            }
        } else alert("Only Chrome25+ browser support voice recognition.")
    }, App.speechCommand = function (a, b) {
        var c = {action: !1, dictation: !1, interim: !1, dictationEnd: !1, dictationEndCommand: "final.", listen: !1};
        $.extend(c, b), a ? c.action ? App.speech_commands.push({command: a, dictation: c.dictation, dictationEnd: c.dictationEnd, dictationEndCommand: c.dictationEndCommand, interim: c.interim, action: c.action, listen: c.listen}) : alert("Must have an action function") : alert("Must have a command text")
    }, App
}(App || {});
$(function () {
    function a(a) {
        for (var b = window.location.search.substring(1), c = b.split("&"), d = 0; d < c.length; d++) {
            var e = c[d].split("=");
            if (e[0] == a)return e[1]
        }
    }

    if ("webkitSpeechRecognition"in window) {
        App.speech({lang: "en"}), App.speechCommand("go to", {action: function () {
            $.gritter.add({title: "Go to Page", text: "Tell me where do you want to go?", image: App.conf.imgPath + "/mic-icon.png", class_name: "clean", time: ""})
        }, listen: function (a) {
            switch (a) {
                case"dashboard":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/index.html?listen=on";
                    break;
                case"sidebar":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/layouts-sidebar.html?listen=on";
                    break;
                case"ui elements":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-elements.html?listen=on";
                    break;
                case"buttons":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-buttons.html?listen=on";
                    break;
                case"icons":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-icons.html?listen=on";
                    break;
                case"grid":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-grid.html?listen=on";
                    break;
                case"tabs":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"accordions":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"tabs and accordions":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-tabs-accordions.html?listen=on";
                    break;
                case"nestable lists":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/ui-nestable-lists.html?listen=on";
                    break;
                case"form elements":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-elements.html?listen=on";
                    break;
                case"validation":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-validation.html?listen=on";
                    break;
                case"wizard":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-wizard.html?listen=on";
                    break;
                case"input masks":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-masks.html?listen=on";
                    break;
                case"text editor":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/form-wysiwyg.html?listen=on";
                    break;
                case"tables":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/tables-general.html?listen=on";
                    break;
                case"data tables":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/tables-datatables.html?listen=on";
                    break;
                case"maps":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/maps.html?listen=on";
                    break;
                case"typography":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/typography.html?listen=on";
                    break;
                case"charts":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/charts.html?listen=on";
                    break;
                case"blank page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-blank.html?listen=on";
                    break;
                case"blank page header":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-blank-header.html?listen=on";
                    break;
                case"login":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-login.html?listen=on";
                    break;
                case"404 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-404.html?listen=on";
                    break;
                case"500 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-500.html?listen=on";
                    break;
                case"500 page":
                    location.href = "http://foxythemes.net/cleanzone/assets/js/pages-500.html?listen=on";
                    break;
                default:
                    $.gritter.add({title: "Error", text: "Could not find: <strong>" + a + "</strong> page, Please try again.", image: App.conf.imgPath + "/mic-icon.png", class_name: "clean", time: ""})
            }
        }}), App.speechCommand("change title", {action: function () {
            $.gritter.add({title: "Change Title", text: "Tell me the new title...", image: App.conf.imgPath + "/mic-icon.png", class_name: "clean", time: ""})
        }, listen: function (a) {
            $(".navbar-brand span").html(a)
        }, interim: function (a) {
            $(".navbar-brand span").html(a)
        }}), App.speechCommand("log out", {action: function () {
            location.href = "http://foxythemes.net/cleanzone/assets/js/pages-login.html"
        }}), App.speechCommand("toggle sidebar", {action: function () {
            App.toggleSideBar()
        }}), App.speechCommand("scroll down", {action: function () {
            var a = $(window).scrollTop();
            $("html, body").animate({scrollTop: a + 500}, "slow")
        }}), App.speechCommand("scroll up", {action: function () {
            var a = $(window).scrollTop();
            $("html, body").animate({scrollTop: a - 500}, "slow")
        }}), App.speechCommand("go bottom", {action: function () {
            $("html, body").animate({scrollTop: $(document).height()}, "slow")
        }}), App.speechCommand("go top", {action: function () {
            $("html, body").animate({scrollTop: 0}, "slow")
        }});
        var b = {action: function () {
            $.gritter.add({title: "Hello", text: "Tell me what is your name?", image: App.conf.imgPath + "/user-icon.png", class_name: "clean", time: ""})
        }, listen: function (a) {
            function b(a) {
                return a.replace(/\w\S*/g, function (a) {
                    return a.charAt(0).toUpperCase() + a.substr(1).toLowerCase()
                })
            }

            var c = b(a);
            $.gritter.add({title: "Clean Zone", text: "Welcome home <strong>" + c + "</strong>!", image: App.conf.imgPath + "/user-icon.png", class_name: "clean", time: ""}), $(".side-user .info a").html(c), $(".profile_menu > a span").html(c)
        }};
        App.speechCommand("hello", b), App.speechCommand("hi", b);
        var c = {action: function () {
            $.gritter.add({title: "Clean Zone", text: "Your welcome!", image: App.conf.imgPath + "/user-icon.png", class_name: "clean", time: ""})
        }};
        App.speechCommand("thanks", c), App.speechCommand("thank you", c), App.speechCommand("email", {dictation: !0, dictationEndCommand: "end of email", dictationEnd: function () {
            var a = $('<div class="progress progress-striped active" style="display:none;"><div style="width: 0%" class="progress-bar progress-bar-info">0%</div></div>').css("margin", "10px 0 0 0");
            $("#mod-info .interim").fadeOut(function () {
                $(this).html("")
            }), a.appendTo("#mod-info .modal-body").fadeIn(), a.find(".progress-bar").animate({width: 900}, {duration: 5e3, step: function (a, b) {
                var c = (100 * a / b.end).toFixed(0);
                $(this).html(c + "%")
            }, complete: function () {
                $("#mod-info").modal("hide")
            }}), $("#mod-info .modal-body h4").html("Thank you!"), $("#mod-info .modal-body p").addClass("text-center").html("We are sending a new e-mail...")
        }, action: function () {
            var a = $('<div role="dialog" tabindex="-1" id="mod-info" class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button></div><div class="modal-body"><div class="text-center"><div class="i-circle primary"><i class="fa fa-envelope"></i></div><h4>Tell me your message...</h4><div contenteditable="true"><p class="text-left"><span class="msg"></span><span class="interim color-primary"></span></p></div></div></div><div class="modal-footer"><button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button><button data-dismiss="modal" class="btn btn-primary" type="button">Send</button></div></div></div></div>');
            a.appendTo("body"), $("#mod-info").modal(), $("#mod-info").on("hidden.bs.modal", function () {
                $(this).removeData("bs.modal"), $(this).remove()
            })
        }, listen: function (a) {
            $("#mod-info .msg").append(" " + a), $("#mod-info .interim").fadeOut(function () {
                $(this).html("")
            })
        }, interim: function (a) {
            $("#mod-info .interim").show().html(a)
        }}), App.speechCommand("stop", {action: function () {
            App.speech("stop")
        }}), "on" == a("listen") && App.speech("start"), $(".speech-button").click(function (a) {
            var b = $('<div role="dialog" tabindex="-1" id="mod-sound" data-backdrop="false" class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button><h2 class="hthin"><img src="' + App.conf.imgPath + '/mic-icon.png"/*tpa=http://foxythemes.net/cleanzone/assets/js/' + App.conf.imgPath + '/mic-icon.png*/ /> Speech API</h2></div><div class="modal-body" style="padding:0 25px;"><div><h4>Voice Recognition</h4><div><p class="text-left">Thanks to Google Speech API we can do <a href="https://dvcs.w3.org/hg/speech-api/raw-file/tip/speechapi.html">Speech Recognition</a> in our web sites, initially Chrome 25+ and up versions support this, but don&#39;t worry! browsers are working on a <a href="https://wiki.mozilla.org/HTML5_Speech_API">standard</a> and soon we will see this working on our favorites browsers. </p><h4 class="spacer2">Let the party begin</h4><p>First you must to allow microphone access clicking on <strong>"Allow"</strong> option above this modal. After this you&#39;ll see the Microphone icon with a blur effect, this means that voice recognition starts.</p><h4 class="spacer2">Voice Commands</h4><p>After that, try to say <strong>"Hello"</strong> at your mic. Do you see what happens? things in template start to change, now try these commands:</p><ul><li><strong>"Go to"</strong>: wait for a message and then say a page title like "tables"</li><li><strong>"Change title"</strong> - Sets template title</li><li><strong>"Scroll down"</strong> and <strong>"Scroll up"</strong></li><li><strong>"Go top"</strong> and <strong>"Go bottom"</strong></li><li><strong>"Toggle sidebar"</strong></li><li><strong>"log out"</strong></li><li><strong>"Thank you"</strong></li><li><strong>"Stop"</strong> - Stops recognition</li><li><strong>"Email"</strong> - Compose an e-mail with your voice, to end dictation just say "end of email"</li></ul><p>Do you want more commands? you can add a voice command easily with our own API.</p></div></div></div><div class="modal-footer"><button data-dismiss="modal" class="btn btn-primary" type="button">DONE!</button></div></div></div></div>');
            b.appendTo("body"), $("#mod-sound").modal(), $("#mod-sound").on("hidden.bs.modal", function () {
                $(this).removeData("bs.modal"), $(this).remove()
            }), App.speech("start"), a.preventDefault(), a.stopPropagation()
        })
    }
});




