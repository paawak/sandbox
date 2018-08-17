$.get("rest/news/", function(data, status) {
	if (status == "success") {
		loadNewsItems(data);
	} else {
		// TODO
	}
});

function loadNewsItems(newsItemsArray) {
	newsItemsArray.forEach(function(newsItem) {
		addNews(newsItem);
	});
}

function addNews(newsItem) {
    var tableDiv = document.getElementById("tableDiv");
    var newDivRow = '<div class="div-table-row">' 
	+ '<div class="div-table-col"><a href=' + newsItem.url + '>' + newsItem.headline + '</a></div>'
	+ '<div class="div-table-col"><a href=http://' + newsItem.publisherUrl + '>' + newsItem.publisherName + '</a></div>'
	+ '<div class="div-table-col">' + newsItem.publishedTime + '</div>'
	+ '</div>'; 
    tableDiv.innerHTML += newDivRow;
}