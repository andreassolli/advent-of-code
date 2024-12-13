with open("./input/Day1.txt") as f:
    s = f.read().strip()

part1 = 0
part2 = 0

def end_number(line):
    for i in range(len(line) - 1, -1, -1):
        if(line[i].isnumeric()):
            return line[i]

def start_number(line):
    for i in range(len(line)):
        if(line[i].isnumeric()):
            return line[i]

numbers = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

def find_number(line):
    for number in numbers:
        if(number in line):
            return numbers.index(number) + 1
    return 0

def start(line):
    index = len(line)
    for i in range(len(line)):
        if i+4 < len(line):
            if find_number(line[i:i+4]) > 0:
                index = i
            if i == index+4: return line[i:i+4]
        if line[i].isnumeric(): return line[i]

def end(line):
    index = 0
    for i in range(len(line) - 1, -1, -1):
        if i-4 > 0:
            if find_number(line[i-4:i]) > 0:
                index = i
            if i == index-4: return line[i:i+4]
        if line[i].isnumeric(): return line[i]

for line in s.split("\n"):
    num = start_number(line) + end_number(line)
    num2 = end(line) + start(line)
    part1 += int(num)
    part2 += int(num2)

print(part1)
print(part2)
