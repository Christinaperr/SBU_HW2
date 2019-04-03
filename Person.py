class Person:
    name: str
    age: int
    wealth: int
    adult: bool

    def __init__(self, name, age):
        self.name = name
        self.age = age
        self.wealth = 0
        self.set_is_adult(self)

    def is_adult(self):
        if self.adult:
            return True
        else:
            return False

    def set_is_adult(self):
        if self.age > 18:
            self.adult = True
        else:
            self.adult = False

    def __str__(self):
        print(self.name, self.age, self.wealth, self.adult);
