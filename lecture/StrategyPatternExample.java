// Strategy Interface
interface Strategy {
    int execute(int a, int b);
}

// Concrete Strategies
class Addition implements Strategy {
    public int execute(int a, int b) {
        return a + b;
    }
}

class Subtraction implements Strategy {
    public int execute(int a, int b) {
        return a - b;
    }
}

// Context
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// Main
public class StrategyPatternExample {
    public static void main(String[] args) {
        Context context = new Context(new Addition());
        System.out.println("Addition: " + context.executeStrategy(5, 3));

        context.setStrategy(new Subtraction());
        System.out.println("Subtraction: " + context.executeStrategy(5, 3));
    }
}