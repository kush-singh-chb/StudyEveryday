package org.example.l75;

public class MeetingScheduler {

    // Class to represent a time interval (start and end time in hours)
    static class Person {
        int day;
        int start;
        int end;

        Person(int day, int start, int end) {
            this.day = day;
            this.start = start;
            this.end = end;
        }

        // Check if two intervals overlap
        public boolean overlaps(Person other) {
            return this.day == other.day && this.start < other.end && this.end > other.start;
        }

        // Get the next available time after the current intervals
        public Person getNextAvailable(Person other) {
            if (this.day == other.day) {
                if(this.end >= 17 || other.end >=17){
                    int nextDay = this.day+1;
                    return new Person(nextDay, 9, 10);
                }
                int nextStart = Math.max(this.end, other.end);
                int nextEnd = nextStart + 1;
                return new Person(this.day, nextStart, nextEnd);
            } else {
                // If they're on different days, the next available time is the earlier of the two
                int nextDay = Math.max(this.day, other.day);
                return new Person(nextDay, 9, 10);  // Schedule at 9:00 AM the next available day
            }
        }
    }
    public boolean areAvailable(final Person p1, final Person p2){
        return !p1.overlaps(p2);
    }

    public static void main(String[] args) {
        var meetingScheduler = new MeetingScheduler();
        Person person1 = new Person(15,0,12);
        Person person2 = new Person(15, 11,17);
        if(meetingScheduler.areAvailable(person1,person2)){
            System.out.println("Both people can meet");
        }else{
            System.out.println("Both people are busy now");
            var newMeeting = person1.getNextAvailable(person2);
            System.out.println("Next available slot is at " + newMeeting.day + " " + newMeeting.start + " " + newMeeting.end);
        }

    }
}