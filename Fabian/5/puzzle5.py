

class seat_checker:

    def __init__(self, seats):
        self.seats = seats

    def get_row(self, partioning_code_row):
        partioning_code_row = partioning_code_row[::-1]
        row_arr = []
        for i in range(len(partioning_code_row)):
            if partioning_code_row[i] == 'B':
                row_arr.append((2**i))
        return sum(row_arr)


    def get_col(self, partioning_code_col):
        partioning_code_col = partioning_code_col[::-1]
        col_multiplier_arr = []
        for i in range(len(partioning_code_col)):
            if partioning_code_col[i] == 'R':
                col_multiplier_arr.append((2**i))
        return sum(col_multiplier_arr)    

    def get_all_row_col(self):
        row_cols = []
        for i in self.seats:
            row_cols.append(self.get_row_col(i))
        return row_cols

    def get_row_col(self, partioning_code):
        rowcol = [self.get_row(partioning_code[:-3]), self.get_col(partioning_code[-3:])]
        return rowcol

    def get_seat_id(self, rowcol):
        return 8*rowcol[0] + rowcol[1]

    def get_all_seat_ids(self):
        seats_ids = []
        for i in self.seats:
            seats_ids.append(self.get_seat_id(self.get_row_col(i)))
        return seats_ids

    def get_highest_id(self):
        return max(self.get_all_seat_ids())

    def find_seat(self):
        all_seats_ids = self.get_all_seat_ids()
        for i in range(self.get_highest_id()):
            if i not in all_seats_ids and i-1 in all_seats_ids and i+1 in all_seats_ids:
                return i

if __name__ == '__main__':
    input_array = []
    with open('input.txt') as my_file:
        for line in my_file:
            line = line.replace('\n', '')
            input_array.append(line)
    st_ch = seat_checker(input_array)
    print(st_ch.find_seat())

    

    
    
   