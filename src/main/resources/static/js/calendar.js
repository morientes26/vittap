
$(document).ready(function() {

    // helper for usual javascript jobs
    var utils = (function(){

        var isEmpty = function(obj) {
            for(var key in obj) {
                if(obj.hasOwnProperty(key))
                    return false;
            }
            return true;
        };

        return {
            isEmpty : isEmpty
        }

    })();

    // event calendar handler - basic operation on calendar
    var calendarHandler = (function() {

        var calendar = $('#calendar');

        var refresh = function(events){
            calendar.fullCalendar('removeEvents');
            calendar.fullCalendar('addEventSource', events);
            calendar.fullCalendar('rerenderEvents' );
        };

        var deleteEvent = function(id){
            $.ajax({
                url: "/event-calendar/delete/"+id,
                type: "GET",
            }).done(function(events) {
                refresh(events);
            });
        };

        var deleteFollowedEvents = function(id){
            //todo: implement delete action on all followed events
        };


        return {
            refresh: refresh,
            deleteEvent: deleteEvent,
            deleteFollowedEvents: deleteFollowedEvents
        }

    })();

    // modal handler
    var modalHandler = (function () {

        var modal = $("#eventModal");
        // html form on modal
        var form = $("#js_modal-form");
        // html button for delete event
        var deleteButton = $(".js_event-delete");

        var clean = function () {
            $(".modal-body #event-id").val('');
            $(".modal-body #event-name").val('');
            $(".modal-body #event-description").val('');
            $(".modal-title").val("Create event");
            $(".modal-footer button[type='submit']").text("Create");
            $(".modal-footer .js_event-delete").hide();
        };

        var loadEvent = function (id) {
            $.getJSON("/event-calendar/data/"+id, function(event) {
                $(".modal-body #event-id").val( event.id );
                $(".modal-body #event-start").val(moment(event.start).format('DD.MM.YYYY HH:mm'));
                $(".modal-body #event-duration").val((event.end - event.start) / 60000);
                $(".modal-body #event-name").val( event.name );
                $(".modal-body #event-description").val( event.description );
            });
        };


        var init = function() {
            actionsBinding();
        };

        // -- private methods

        var actionsBinding = function(){

            // submit modal (create or update event)
            form.on('submit', function(event) {
                var $form = $(this);
                var $modal = modal;

                $.ajax({
                    type: $form.attr('method'),
                    url: $form.attr('action'),
                    data: $form.serialize(),

                    success: function(events) {
                        $modal.modal('hide');
                        calendarHandler.refresh(events);
                    }
                });

                event.preventDefault();
            });

            // load data to modal after cell click
            modal.on('show.bs.modal', function (e) {

                clean();
                var event = $(e.relatedTarget).data('event-object');
                //$(".modal-body #event-start").val( moment(event.start).format('DD.MM.YYYY HH:mm'));
                $(".modal-body #event-start").val( event.start);
                //$(".modal-body #event-start").val( dateTimeZone(event.start).format('DD.MM.YYYY HH:mm'));

                if (event.id!=null){
                    // exist event
                    $(".modal-title").val("Edit event");
                    $(".modal-footer button[type='submit']").text("Update");
                    $(".modal-footer .js_event-delete").show();
                    modalHandler.loadEvent(event.id);
                }
            });

            // delete item from calendar
            deleteButton.bind('click', function () {
                var event = $(".js_modal-click").data('event-object');
                $('#eventModal').modal('hide');
                $("#delete-dialog-confirm").data('event', event).dialog("open");
            });
        };

        return {
            init: init,
            clean : clean,
            loadEvent: loadEvent
        }

    })();

    modalHandler.init();


    // -------- initialization of component event calendar --------------------

    $("#calendar").fullCalendar({
        schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
        aspectRatio: 1,
        businessHours: {
            dow: [ 1, 2, 3, 4, 5, 6 ], // Mo - Sa
            start: '09:00', // a start time
            end: '22:00' // an end time
        },
        hiddenDays: [ 0 ],
        firstDay: 1,
        minTime: '09:00',
        maxTIme:'22:00',
        timezone: 'UTC-3',//'America/Montevideo',
        allDaySlot: false,
        defaultView: 'agendaThreeDay',
        editable: false,  // cannot edit event
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaThreeDay,agendaDay'
        },
        eventLimit: true,
        views: {
            agendaThreeDay: {
                type: 'agenda',
                duration: { days: 3 },
                buttonText: '3 day',
                eventLimit: 3
            }
        },
        selectable: true,
        events: '/event-calendar/data',  // read json data from controller
        nowIndicator: true, // show actual hour

        eventClick: function(calEvent, jsEvent, view) {

            $(".js_modal-click").data('event-object', calEvent).click();

        },
        // render other flags
        eventRender: function(event, el) {

            // select view type
            var fs = $('.js_show').find(":selected").text();
            var show=1;
            if (fs=='Empty Seats Class'){
                show=2;
            } else if (fs=='Empty Seats Course'){
                show=3;
            }

            if (utils.isEmpty(event.name)) {
                event.name = '';
            }
            if (utils.isEmpty(event.teacher)) {
                event.teacher = '';
            }

            el.find('.fc-content').attr('title', event.description);
            el.find('.fc-title').after($('<span class="fc-name">' + event.name + '</span>'));
            el.find('.fc-name').after($('<span class="fc-teacher"> - ' + event.teacher + '</span>'));

            if (show==1) {

                if (event.count > 1) {
                    el.find('.fc-teacher').after(
                        $(
                          '<img class="fc-image" src="/static/img/some.png"/>'+
                          '<span class="fc-count">[' + event.count + ']</span>'
                        )
                    );
                } else if (event.count == 1) {
                    el.find('.fc-teacher').after(
                        $(
                          '<img class="fc-image" src="/static/img/one.png"/>' +
                          '<span class="fc-count">[' + event.count + ']</span>'
                        )
                    );
                }
            }

            if (show==2){


            }

            if (show==3){


            }

        },
        select: function (start, end, jsEvent, view) {

            var newEvent = {
                title : 'default',
                start : moment(start).format('DD.MM.YYYY HH:mm'),
                allDay: false
            };

            $(".js_modal-click").data('event-object', newEvent).click();

        }
    });

    // -------- initialization of delete dialog confirm component ------------

    $( "#delete-dialog-confirm" ).dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "Single event": function() {
                var event = $(this).data('event');
                calendarHandler.deleteEvent(event.id);
                $( this ).dialog( "close" );
            },
            "All followed events": function() {
                var event = $(this).data('event');
                calendarHandler.deleteFollowedEvents(event.id);
                $( this ).dialog( "close" );
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    // --------  binding other html event actions ----------------------------

    $(".js_filter").keyup(function(){

        var form = $('#filterForm');

        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: form.serialize()
        }).done(function(events) {
            calendarHandler.refresh(events);
        }).fail(function(xhr, status, error) {
            console.log(error);
        });
    });

    $(".js_show").change(function(){

        var form = $('#filterForm');

        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: form.serialize()
        }).done(function(events) {
            calendarHandler.refresh(events);
        }).fail(function(xhr, status, error) {
            console.log(error);
        });
    });

    // init component
    $(":input").inputmask();

});
