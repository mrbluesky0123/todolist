function loadSearchResult() {

    $.ajax({
        type: 'get',
        url: '/refreshtodolist',
        success: function (data){
            $('#todo-div').html(data);
        }
    })

}

