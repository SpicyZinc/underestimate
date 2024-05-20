/*
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.

Return a list of transactions that are possibly invalid. You may return the answer in any order.

Example 1:
Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.

Example 2:
Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]

Example 3:
Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]

Constraints:
transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.

idea:
direct idea
just save all transactions for same person in a map
then compare each transactions against this user's all transactions
*/

class InvalidTransactions {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> invalid = new ArrayList<>();
        Map<String, List<String>> hm = new HashMap<>();

        for (String transaction : transactions) {
            String[] matches = parseTransaction(transaction);
            String name = matches[0];
            hm.computeIfAbsent(name, x -> new ArrayList<String>()).add(transaction);
        }
        // loop again
        for (String transaction : transactions) {
            String[] matches = parseTransaction(transaction);
            String name = matches[0];
            if (!isValid(transaction, hm.get(name))) {
                invalid.add(transaction);
            }
        }

        return invalid;

    }

    public String[] parseTransaction(String transaction) {
        String[] matches = transaction.split(",");
        return matches;
    }

    public boolean isValid(String t, List<String> list) {
        String[] matches = parseTransaction(t);
        int time = Integer.parseInt(matches[1]);
        int amount = Integer.parseInt(matches[2]);
        String city = matches[3];

        if (amount > 1000){
            return false;
        }

        for (String transaction : list) {
            String[] splits = parseTransaction(transaction);
            int otherTime = Integer.parseInt(splits[1]);
            String otherCity = splits[3];

            if (Math.abs(otherTime - time) <= 60 && !otherCity.equals(city)){
                return false;
            }
        }
        return true;
    }
}
