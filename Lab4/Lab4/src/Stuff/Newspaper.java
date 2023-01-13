package Stuff;

import Citizens.LoonarCitizen;

public class Newspaper {

    private String newspaperName;
    private String date;

    public Newspaper(String name, String date) {
        this.newspaperName = name;
        this.date = date;
    }

    public class News {

        private String newsHeader;
        private String newsBody;
        private LoonarCitizen author;


        public News(String header, String body) {
            this.newsHeader = header;
            this.newsBody = body;
        }

        public News(String header, String body, LoonarCitizen author) {
            this.newsHeader = header;
            this.newsBody = body;
            this.author = author;
        }


        public String getHeader() {
            return this.newsHeader;
        }

        public String getBody() {
            return this.newsBody;
        }

        public String getAuthorName() {
            return this.author == null ? "Аноним" : this.author.getName();
        }

        public String getNewspaperName() {
            return Newspaper.this.newspaperName;
        }

        public String toString() {
            return this.newsHeader + ": " + this.newsBody + " (Автор: " + this.getAuthorName() + ")";
        }

        public boolean equals(News news) {
            if (this == news) return true;
            if (news == null || getClass() != news.getClass()) return false;
            return this.newsHeader.equals(news.getHeader()) && this.newsBody.equals(news.getBody()) &&
                    Newspaper.this.newspaperName.equals(news.getNewspaperName());
        }

        public int hashCode() {
            return ((this.newsHeader == null ? 0 : this.newsHeader.hashCode()) * 41 -
                    (this.newsBody == null ? 0 :this.newsBody.hashCode()) * 7);
        }

    }
}
