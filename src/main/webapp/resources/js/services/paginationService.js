app.factory('paginationService', function () {

    function buildPagination(page, rl) {
        var links = [];
        if (page.totalPages <= 5) {
            addLinks(rl, 2, 4, links);
            links = addCommonLinks(links, page, rl);
        } else {
            links = addCommonLinks(generateAdditionalLinks(page, rl), page, rl);
        }
        return links;
    }

    function generateAdditionalLinks(page, rl) {
        var window = 3;
        var additionalLinks = [];
        var cp = page.number + 1;
        var lp = page.totalPages;
        if (cp < window + 1 && cp <= lp - window) {
            addLinks(rl, 2, window + 1, additionalLinks);
            addDots(additionalLinks);
        } else if (cp >= window + 1 && cp <= lp - window) {
            addDots(additionalLinks);
            addLinks(rl, cp - 1, cp + 1, additionalLinks);
            addDots(additionalLinks);
        } else if (cp >= 1 + window && cp > lp - window) {
            addDots(additionalLinks);
            addLinks(rl, lp - window, lp - 1, additionalLinks);
        }
        return additionalLinks;
    }

    function addLinks(rl, first, last, additionalLinks) {
        for (var i = first; i <= last; i++) {
            additionalLinks.push(new Link(i, rl['first'].href.replace("page=0", "page=" + (i - 1).toString())));
        }
    }

    function addDots(additionalLinks) {
        additionalLinks.push(new Link('..', 'disabled'));
    }

    function addCommonLinks(links, page, rl) {
        links.unshift(new Link(1, rl['first'].href));
        links.unshift(new Link('<', rl.hasOwnProperty('prev') ? rl['prev'].href : 'disabled'));
        links.push(new Link(page.totalPages, rl['last'].href));
        links.push(new Link('>', rl.hasOwnProperty('next') ? rl['next'].href : 'disabled'));
        return links;
    }

    var Link = function (name, value) {
        this.name = name;
        this.value = value;
    };

    return {
        buildPagination
    }

});