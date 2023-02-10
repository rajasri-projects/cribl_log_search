<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Cribl - Log Search">
    <meta name="keywords" content="Log Search, Application Log Search, Cribl Log Search">
    <title>Cribl - Log Search</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
    <link rel="icon" type="image/x-icon" href="resources/images/cribl.png">
    <link rel="stylesheet" href="resources/css/search.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8 logo text-center">
                <img src="resources/images/cribl.png" height="100px;" width="100px;" /> Cribl - Log Search
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8 text-center">
                <form class="searchForm">
                    <div id="messageSearchFormId"></div>
                    <div class="col-md-12 mb-3">
                        <input type="text" class="form-control" id="searchInputTextId">
                        <div class="invalid-feedback">
                            Please provide a valid input for search
                        </div>
                    </div>
                    <button type="submit" class="btn btn-secondary" id="searchSubmitBtnId">Search</button>
                </form>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <script src="resources/scripts/search.js"></script>
    <script src="resources/scripts/common.js"></script>
</body>
</html>