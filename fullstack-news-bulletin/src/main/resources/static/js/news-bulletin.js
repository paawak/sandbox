$.get("rest/news/", function(data, status) {
	if (status == "success") {
		loadNewsItems(data);
	} else {
		// TODO
	}
});

function loadNewsItems(newsItemsArray) {
	newsItemsArray.forEach(function(newsItem) {
		console.log(newsItem);
	});
}