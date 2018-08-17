$.ajax({
    url: 'rest/news/',
    type: 'GET',
    success: function(data){ 
    	loadNewsItems(data);
    },
    error: function(data) {
    	showAlert();
    }
});

function loadNewsItems(newsItemsArray) {
	newsItemsArray.forEach(function(newsItem) {
		addNews(newsItem);
	});
}

function addNews(newsItem) {
    var tableDiv = document.getElementById("tableDiv");
    var newDivRow = '<div class="row">' 
	+ '<div class="col-6"><a href=' + newsItem.url + '>' + newsItem.headline + '</a></div>'
	+ '<div class="col"><a href=http://' + newsItem.publisherUrl + '>' + newsItem.publisherName + '</a></div>'
	+ '<div class="col">' + newsItem.publishedTime + '</div>'
	+ '</div>'; 
    tableDiv.innerHTML += newDivRow;
}

function showAlert() {
	var alertMessage = '<div class="alert alert-warning alert-dismissible fade show"' +'		role="alert">' +'		<strong>Sorry!</strong> Could not load news items.' +'		<button type="button" class="close" data-dismiss="alert"' +'			aria-label="Close">' +'			<span aria-hidden="true">&times;</span>' +'		</button>' +'	</div>';
	var divAlert = document.getElementById("divAlert");
	divAlert.innerHTML += alertMessage;
}