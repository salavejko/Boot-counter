# Boot-counter
The aim of this application is to monitor when the device boots up and to set up a repeating task for displaying notifications containing boot event details. Users can dismiss these notifications, which will prompt the app to reschedule them accordingly.

## TODOs

1. **Handle Empty UI States**: Ensure that all UI components handle empty or null states gracefully.
2. **Make Notifications Work**: Make boot counter action notifications functioning correctly.
3. **Implement Notification Logic**:
   - **Total Dismissals Allowed**: Add logic to handle the total number of dismissals allowed for notifications.
   - **Interval Between Dismissals**: Implement logic to set and enforce the interval between consecutive dismissals.
   - **Notification Dismiss Action**: Implement the action for dismissing notifications.
   - **Time Between Last Two Boot Events**: Implement and track the time between the last two boot events.
4. **Test and Beautify Code**: 
   - Conduct thorough testing to ensure functionality and stability.
   - Refactor code to improve readability, maintainability, and performance.
