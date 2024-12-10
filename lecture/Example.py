from abc import ABC, abstractmethod

# Strategy Interface
class Strategy(ABC):
    @abstractmethod
    def execute(self, a, b):
        pass

# Concrete Strategies
class Addition(Strategy):
    def execute(self, a, b):
        return a + b

class Subtraction(Strategy):
    def execute(self, a, b):
        return a - b

# Context
class Context:
    def __init__(self, strategy: Strategy):
        self._strategy = strategy

    def set_strategy(self, strategy: Strategy):
        self._strategy = strategy

    def execute_strategy(self, a, b):
        return self._strategy.execute(a, b)

# Main
if __name__ == "__main__":
    context = Context(Addition())
    print("Addition:", context.execute_strategy(5, 3))

    context.set_strategy(Subtraction())
    print("Subtraction:", context.execute_strategy(5, 3))