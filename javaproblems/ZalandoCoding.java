package javaproblems;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


// @Immutable
class CodeReview implements Comparable<CodeReview> { // final class to prevent being overriden

    // make state variables final
    private int m_id;
    private String m_type;
    private String m_name;
    private List m_keys; // may be better to have a type indication of possible key values that can be stored

    // String example = "codility";
    // String ex2 = new String("codility");
    public CodeReview(final int id, final String type, final String name, final List keys) {
        this.m_id = m_id;
        this.m_type = m_type;
        this.m_name = m_name;
        this.m_keys = m_keys;
    }

    public int getId() {
        return m_id;
    }

    public String getType() {
        return m_type;
    }

    public String getName() {
        return m_name;
    }

    public List getM_keys() {
        return m_keys;
    }

    // make it more clear by having @Override annotation for overriding Object methods
    public boolean equals(final CodeReview other) {

        return m_id == other.m_id && m_name.equals(other.m_name);
    }

    // BUG: equal returns true for same id, name. but they could have different type values
    // resulting in hashCode() returning different hash values as we include type also
    // in hash value calculation
    public int hashCode() {
        return (m_id * 31 + m_type.hashCode()) * 31 + m_name.hashCode();
    }

    // probably also check id as its used in equals()
    @Override
    public int compareTo(CodeReview o) {
        return getName().compareTo(o.getName());
    }

}

// We want to provide a special treatment for every 1000th requests to our new fancy service. Given the following snippet,
// please provide an implementation for drawLot such that exactly every 1000th invocation receives a prize.

enum Variant {
  BLANK, PRIZE;
}

@Service
@Singleton
class Lottery {

  AtomicInteger requestSeqNumber = new AtomicInteger(0);
  Variant drawLot() {
    int currSeqNumber = requestSeqNumber.incrementAndGet();
    if((currSeqNumber % 1000) == 0){
        // reset this value back to 0
        requestSeqNumber.compareAndSet(currSeqNumber, 0);
        return Variant.PRIZE;
    }
    return Variant.BLANK;
  }
}