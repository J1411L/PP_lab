#include <iostream>
#include <memory>

// Strategy Interface
class Strategy {
public:
    virtual int execute(int a, int b) = 0;
    virtual ~Strategy() = default; // Виртуальный деструктор
};

// Concrete Strategies
class Addition : public Strategy {
public:
    int execute(int a, int b) override {
        return a + b;
    }
};

class Subtraction : public Strategy {
public:
    int execute(int a, int b) override {
        return a - b;
    }
};

// Context
class Context {
private:
    std::unique_ptr<Strategy> strategy;

public:
    Context(std::unique_ptr<Strategy> strategy) : strategy(std::move(strategy)) {}

    void setStrategy(std::unique_ptr<Strategy> newStrategy) {
        strategy = std::move(newStrategy);
    }

    int executeStrategy(int a, int b) {
        return strategy->execute(a, b);
    }
};

// Main
int main() {
    Context context(std::make_unique<Addition>());
    std::cout << "Addition: " << context.executeStrategy(5, 3) << std::endl;

    context.setStrategy(std::make_unique<Subtraction>());
    std::cout << "Subtraction: " << context.executeStrategy(5, 3) << std::endl;

    return 0;
}