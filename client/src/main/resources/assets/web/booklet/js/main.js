function initPages() {
    const $book = $('#flipbook');
    const $PAGES = 28;
    for (let i = 1; i <= $PAGES; i++) {
        let $page = $('<div>').addClass(`hard sheet p${i}`);
        let nth = i < 10 ? `0${i}` : i;
        let $content = $('<img>').addClass('page').attr('src', `res/booklet-${nth}.png`);
        $page.append($content);
        $book.append($page);
    }
}


function initBook() {

    let $flipbook = $("#flipbook").turn({
        acceleration: true,
        autoCenter: true,
        pages: 10,
        when: {
            turning: handleTurning
        }
    });
    $('#btPrev').hide();
    /* intiialize control click handlers */
    $('#btPrev').on('click', function () {
        $flipbook.turn('previous');
    })
    $('#btNext').on('click', function () {
        $flipbook.turn('next');
    })
}

const FIRST_PAGE = 1;
const LAST_PAGE = 28;
function handleTurning(event, page, obj) {
    playTurn2();
    updateBtns(page);
}

function playTurn2() {
    let audio = new Audio("./res/turn2.mp3");
    audio.play();
}

function updateBtns(page) {
    if (page === FIRST_PAGE) {
        $('#btPrev').hide();
    } else if (page === LAST_PAGE) {
        $('#btNext').hide();
    } else {
        $('#btPrev').show();
        $('#btNext').show();
    }
}

$(document).ready(function () {
    initPages();
    initBook();
});