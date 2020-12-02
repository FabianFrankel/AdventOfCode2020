

class password_solver:


    def __init__(self, passwords):
        self.passwords = passwords
        


    def print(self):
        print(self.passwords)

    def get_allowed_passwords(self, policy):
        allowed_passwords = []
        for string in self.passwords:
           if(self.check_if_pwd_allowed(string, policy)):
               allowed_passwords.append(string)
        return allowed_passwords

    def check_if_pwd_allowed(self, pwd, policy):
        if(policy == '1'):
            return self._policy_one(pwd)
        elif(policy == '2'):
            return self.policy_two(pwd)
        else:
            print('Fel policy din shono')
            return False

    def policy_two(self, pwd):
        return 0

    def _policy_one(self, pwd):
        lower_constraint = int(pwd.split('-')[0])
        upper_constraint = int(pwd.split('-')[1].split(' ')[0])
        letter_constrained = pwd.split(':')[0][-1]
        return pwd.split(':')[1].count(letter_constrained) <= upper_constraint and pwd.split(':')[1].count(letter_constrained) >= lower_constraint    

if __name__ == '__main__':
    input_array = []
    with open('input.txt') as my_file:
        for line in my_file:
            input_array.append(line)
    pwd_solver = password_solver(input_array)
    
    