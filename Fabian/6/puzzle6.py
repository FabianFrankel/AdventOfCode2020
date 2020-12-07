

class customs_checker:

    def __init__(self, answers_array):
        self.answers_array = answers_array

    def count_number_of_yes_for_group(self, group_answer_array):
        answer_set = self.get_answer_set(group_answer_array)
        return(len(answer_set))


    def get_answer_set(self, group_answer_array):
        answer_set = set()
        for i in group_answer_array:
            for j in i:
                answer_set.add(j)
        return answer_set


    def get_sum_of_group_yes(self):
        ans_sum = 0
        for i in self.answers_array:
            ans_sum = self.count_number_of_yes_for_group(i) + ans_sum
        return ans_sum

    def get_sum_of_exclusive_yes(self):
        ans_sum = 0
        for i in self.answers_array:
            ans_sum = self.count_number_of_exclusive_yes_for_group(i) + ans_sum
        return ans_sum


    def count_number_of_exclusive_yes_for_group(self, group_answer_array):
        print(group_answer_array)
        answer_set = self.get_exclusive_yes_answer_set(group_answer_array)
        return(len(answer_set))

    def get_exclusive_yes_answer_set(self, group_answer_array):
        answer_arr = []
        next_answers = []
        first = True
        for i in group_answer_array:
            for j in i:
                if first:
                    answer_arr.append(j)
                elif j in answer_arr:
                    next_answers.append(j)
            if not first:
                answer_arr = next_answers
                next_answers = []    
            first = False
        return answer_arr



















def get_input_array():
    input_array = []
    temp = []
    with open('input.txt') as my_file:
        for line in my_file:
            if(line == '\n'):
                input_array.append(temp)
                temp = []
            else:
                line = line.replace('\n','')
                temp.append(line)
        input_array.append(temp)
    return input_array




if __name__ == '__main__':
    input_array = get_input_array()
    customs_checker = customs_checker(input_array)
    print(customs_checker.get_sum_of_exclusive_yes())
    
    


            