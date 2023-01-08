document.addEventListener('DOMContentLoaded', () => {
    const datePicker = document.getElementById("date-picker");
    const calendar = new VanillaCalendar('#calendar', {
        type:'default',
        actions: {
            clickDay(event, dates) {
                datePicker.value = dates;
                console.log(datePicker.value)
            },
        }
    });
    calendar.init();

    fetch("/hello")
        .then(res => res.text())
        .then(data => console.log(data))
});