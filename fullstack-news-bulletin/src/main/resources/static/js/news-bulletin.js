fetchNews('rest/news/');

$("#btnSearchNews").click(function() {
	var searchTerm = $('#inputSearchNews').val();
	if (searchTerm.trim() == '') {
		alert("The search term is blank, please enter a value");
		return;
	}
	
	fetchNews('rest/news/search/' + searchTerm.trim());	
	
});

function fetchNews(url) {
	$.ajax({
	    url: url,
	    type: 'GET',
	    success: function(data){ 
	    	$(".row").remove();
	    	loadNewsItems(data);
	    },
	    error: function(data) {
	    	showAlert('Could not load news items.');
	    }
	});
}

function loadNewsItems(newsItemsArray) {
	if (newsItemsArray.length == 0) {
		showAlert('No news items found!');
		return;
	}
	addNewsTableHeader();
	newsItemsArray.forEach(function(newsItem) {
		addNews(newsItem);
	});
}

function addNews(newsItem) {
    var newDivRow = '<div class="row">' 
	+ '<div class="col-6"><a href="' + newsItem.url + '">' + newsItem.headline + '</a></div>'
	+ '<div class="col"><a href="http://' + newsItem.publisherUrl + '">' + newsItem.publisherName + '</a></div>'
	+ '<div class="col">' + newsItem.publishedTime + '</div>'
	+ '</div>'; 
    $("#tableDiv").append(newDivRow);
}

function addNewsTableHeader() {
    var newDivRow = '<div class="row">' 
	+ '<div class="col-6">Headline</div>'
	+ '<div class="col">Publisher</div>'
	+ '<div class="col">Published On</div>'
	+ '</div>'; 
    $("#tableDiv").append(newDivRow);
}

function showAlert(message) {
	var alertMessage = '<div class="alert alert-warning alert-dismissible fade show"' +'		role="alert">' +'		<strong>Sorry!</strong> ' + message +'		<button type="button" class="close" data-dismiss="alert"' +'			aria-label="Close">' +'			<span aria-hidden="true">&times;</span>' +'		</button>' +'	</div>';
	$("#divAlert").append(alertMessage);
}