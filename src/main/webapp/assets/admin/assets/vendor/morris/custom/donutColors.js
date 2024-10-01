// Morris Donut
Morris.Donut({
	element: 'donutColors',
	data: [
		{value: 30, label: 'foo'},
		{value: 15, label: 'bar'},
		{value: 10, label: 'baz'},
		{value: 5, label: 'A really really long label'}
	],
	backgroundColor: '#ffffff',
	labelColor: '#ffffff',
	colors:['#7943ef', '#9166ed', '#ae90ee', '#cdbeef'],
	resize: true,
	hideHover: "auto",
	gridLineColor: "#dddddd",
	formatter: function (x) { return x + "%"}
});