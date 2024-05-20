/*
Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl. 
Return all urls obtained by your web crawler in any order.

idea:
dfs and hashset
*/

class WebCrawler {
    Set<String> visited = new HashSet<>();

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostName(startUrl);

        dfs(startUrl, hostname, htmlParser);

        return new ArrayList<String>(visited);
    }

    // Tue Mar 14 23:47:57 2023 BFS
    public List<String> bfs(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostName(startUrl);

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(startUrl);

        while (!queue.isEmpty()) {
            String currentUrl = queue.poll();
            
            visited.add(currentUrl);

            for (String url : htmlParser.getUrls(currentUrl)) {
                if (getHostName(url).equals(hostname) && !visited.contains(url)) {
                    queue.add(url);
                }
            }
        }

        return new ArrayList<String>(visited);
    }

    public void dfs(String startUrl, String hostname, HtmlParser htmlParser) {
        if (!getHostName(startUrl).equals(hostname) || visited.contains(startUrl)) {
            return ;
        }

        List<String> urls = htmlParser.getUrls(startUrl);

        visited.add(startUrl);
        for (String url : urls) {
            dfs(url, hostname, htmlParser);
        }
    }

    // Get hostname without https:// 
    public String getHostName(String startUrl) {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i < startUrl.length(); i++) {
            // Break when we get to the path
            if (startUrl.charAt(i) == '/') {
                break;
            }
            sb.append(startUrl.charAt(i));
        }

        return sb.toString();
    }
}
