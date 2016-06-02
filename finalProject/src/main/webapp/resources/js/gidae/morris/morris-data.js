// Morris.js Charts sample data for SB Admin template

$(function() {
		
	$.ajax({
		url: '/publicdata',
		type: 'post',
		dataType: 'json',
		success: successHandler,
		error:function(){
			alert('dd2222222222222');
			alert('데이터가없음');
		    Morris.Line({
		        // ID of the element in which to draw the chart.
		        element: 'morris-line-chart',
		        // Chart data records -- each entry in this array corresponds to a point on
		        // the chart.
		        data: [{
		            d: '2012',
		            visits: 802
		        }, {
		            d: '2013',
		            visits: 783
		        }, {
		            d: '2014',
		            visits: 820
		        }, {
		            d: '2015',
		            visits: 839
		        } ],
		        // The name of the data record attribute that contains x-visitss.
		        xkey: 'd',
		        // A list of names of data record attributes that contain y-visitss.
		        ykeys: ['visits'],
		        // Labels for the ykeys -- will be displayed when you hover over the
		        // chart.
		        labels: ['Visits'],
		        // Disables line smoothing
		        smooth: false,
		        resize: true
		    });
	
		}
	});
	function successHandler(data) {
		alert("dd");
		   Morris.Line({
		        // ID of the element in which to draw the chart.
		        element: 'morris-line-chart',
		        // Chart data records -- each entry in this array corresponds to a point on
		        // the chart.
		        data: data,
		        // The name of the data record attribute that contains x-visitss.
		        xkey: "d",
		        // A list of names of data record attributes that contain y-visitss.
		        ykeys: ['관리비'],
		        // Labels for the ykeys -- will be displayed when you hover over the
		        // chart.
		        labels: ['관리비'],
		        // Disables line smoothing
		        smooth: false,
		        resize: true
		    });
	};



});
