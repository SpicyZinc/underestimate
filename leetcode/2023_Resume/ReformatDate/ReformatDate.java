class ReformatDate {
    public String reformatDate(String date) {
        String[] units = date.split("\\s+");

        
        String day = getDay(units[0]);
        String month = getMonth(units[1]);

        return units[2] + "-" + month + "-" + day;
    }

    public String getDay(String d) {
        String num = d.substring(0, d.length() - 2);

        return num.length() == 1 ? "0" + num : num;
    }

    public String getMonth(String m) {
        int month = -1;
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(m)) {
                month = i + 1;
                break;
            }
        }

    
        return month < 10 ? "0" + month : String.valueOf(month);
    }
}
