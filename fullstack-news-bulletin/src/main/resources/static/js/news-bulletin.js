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

$("#btnSearchNews").click(function() {
	  alert( "Handler for .click() called." );
	  $(".row").remove();
});

function loadNewsItems(newsItemsArray) {
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

function showAlert() {
	var alertMessage = '<div class="alert alert-warning alert-dismissible fade show"' +'		role="alert">' +'		<strong>Sorry!</strong> Could not load news items.' +'		<button type="button" class="close" data-dismiss="alert"' +'			aria-label="Close">' +'			<span aria-hidden="true">&times;</span>' +'		</button>' +'	</div>';
	$("#divAlert").append(alertMessage);
}