package model;

public class FootBall {

    private String player;
    private String club;
    private String year;

    public  Builder build(){
        return new Builder();
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    FootBall(Builder builder){
        player=builder.player;
        club = builder.club;
        year=builder.year;
    }

    public static  Builder newBuilder(){
        return new Builder();
    }
    public static final class Builder {
        private String player;
        private String club;
        private String year;

        public Builder withPlayerName(String val){
            this.player =val;
            return this;
        }

        public Builder withClubName(String val){
            this.club=val;
            return this;
        }

        public Builder withYear(String val){
            this.year= val;
            return this;
        }

        public FootBall build(){
            return new FootBall(this);
        }

        public FootBall build(String row){
            if(null!=row){
                String[] cols = row.split(",");
                this.player=cols[0];
                this.club=cols[1];
                this.year=cols[2];
                return new FootBall(this);
            }
            return null;
        }
    }
}
