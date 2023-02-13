class DetermineIfTwoEventsHaveConflict {
    public static void main(String[] args) {
        String[] event1 = new String[] {"14:13","22:08"};
        String[] event2 = new String[] {"02:40","08:08"};

        DetermineIfTwoEventsHaveConflict eg = new DetermineIfTwoEventsHaveConflict();
        eg.haveConflict(event1, event2);
    }

    public boolean haveConflict(String[] event1, String[] event2) {
        String start1 = event1[0];
        String end1 = event1[1];
        
        String start2 = event2[0];
        String end2 = event2[1];
        
        int x1 = getTimeInMinutes(start1);
        int y1 = getTimeInMinutes(end1);
        int x2 = getTimeInMinutes(start2);
        int y2 = getTimeInMinutes(end2);
                    
        return x1 <= x2 && x2 <= y1 || x1 <= y2 && y2 <= y1 || x1 <= x2 && y1 >= y2 || x1 >= x2 && y1 <= y2;
    }
    
    public int getTimeInMinutes(String time) {
        String[] hoursAndMins = time.split(":");
        return Integer.parseInt(hoursAndMins[0]) * 60 + Integer.parseInt(hoursAndMins[1]);
    }
}