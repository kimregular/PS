
n = int(input())

class Student:

    def __init__(self, name, kor, eng, math):
        self.name = name
        self.kor = kor
        self.eng = eng
        self.math = math

students = []


for _ in range(n):
    name, kor, eng, math = input().split()
    students.append(Student(name, int(kor), int(eng), int(math)))

students.sort(key=lambda s: (-s.kor, s.eng, -s.math, s.name))

for s in students:
    print(s.name)