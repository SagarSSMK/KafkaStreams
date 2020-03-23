package model;

import java.util.Objects;

public class Key {
    private Long key;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return Objects.equals(key, key.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "Key{" +
                "orderId=" + key +
                '}';
    }

    public Key(Builder builder){
        this.key=builder.key;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static final class Builder{
        private Long key;

        public Builder withOrderId(Long key){
            this.key=key;
            return this;
        }

        public Key build(){
            return new Key(this);
        }
    }
}
