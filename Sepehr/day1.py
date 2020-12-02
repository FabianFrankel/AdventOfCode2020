
class Day1:

    def __init__(self, file_name):
        # To save all sums below 2020 to be used in part b
        self.under2020 = []
        self.file_name = file_name
        self.sort_list = self._format_data(file_name)

    def _format_data(self, file_name):
        '''Reads the input data and returns a sorted list of integers'''

        input = open('../Inputs/'+file_name, 'r').read()
        # Splits the string and converts each value from str (or something else) to int
        list_int = [int(num) for num in input.split()]
        # Sort the list and return
        list_int.sort()
        return list_int

    def entry_adder(self, target):
        """
        Finds two integers in list_sum that adds up to value target and returns
        the product of those numbers blabla
        """

        # Iterating from front and back in order to avoid adding two small or big
        # numbers
        for i in range(len(self.sort_list)):
            # Reverse loop
            for j in range(len(self.sort_list)-1, 0, -1):

                sum = self.sort_list[i]+self.sort_list[j]
                if sum == target:
                    return( self.sort_list[i], self.sort_list[j],
                            self.sort_list[i] * self.sort_list[j]
                            )
                # Save all values below 2020 in part b
                elif sum < target:
                    self.under2020.append([ self.sort_list[i] + self.sort_list[j],
                                            self.sort_list[i], self.sort_list[j]
                                            ])

    def entry_three_adder(self, target):
        for num_a in self.sort_list:
            for num_b in self.under2020:
                if num_a + num_b[0] == target:
                    return num_a, num_b[1], num_b[2], num_a * num_b[1] * num_b[2]

if __name__ == '__main__':
    advent = Day1('input1.txt')
    product = advent.entry_adder(2020)

    print(product)
    print(advent.entry_three_adder(2020))
