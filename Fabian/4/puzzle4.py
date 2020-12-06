
import re


class passport_checker:


    def __init__(self, passports):
        self.passports = passports
        self.fields = ['byr','iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid' , 'cid']


    def get_valid_passports(self):
        valid_psp = []
        for psp in self.passports:
            if(self._is_present(psp)):
                valid_psp.append(psp)
        return valid_psp

    def get_number_of_valid_passports(self):
        return len(self.get_valid_passports())
        
    def _is_present(self, psp):
        failed_checks = self._get_failed_fields(psp)
        if(len(failed_checks) > 1):
            return False
        elif(len(failed_checks) == 0 or failed_checks[0] == "cid"):
            return True
        return False



    def is_valid(self, psp):
        psp = psp.replace('\n',' ')
        print(psp)
        psp = psp.split(' ')
        for field in psp:
            if(not self.check_field(field)):
                return False
        return True

    def check_field(self, field_pair):
        field_pair.split(':')
        print("dasdaas",field_pair)
        field = field_pair[0]
        value = field_pair[1]
        if field == 'hgt':
            return self.hgt_check(value)
        elif field == 'byr':
            return self._byr_check(value)
        elif field == 'hcl':
            return self._hcl_check
        elif field == 'pid':
            return self._pid_check
        else:
            return True

    def hgt_check(self, value):
        unit = value[-2:]
        amount = value[:-2]
        if (unit == 'cm'):
            if (int(amount) < 150 or int(amount) > 193):
                return False
        else:
            if (int(amount) < 59 or int(amount) > 76):
                return False
        return True

    def byr_check(value):
        if (int(value) < 2010 or int(value) > 2020):
            return False
        return True

    def eyr_check(value):
        if (int(value) < 2020 or int(value) > 2030):
            return False
        return True

    def print_passports(self):
        print(self.passports)
        
    def _get_failed_fields(self, psp):
        failed_checks = []
        for check in self.fields:
            if not (check+':') in psp:
                failed_checks.append(check)
        return failed_checks





def get_array_of_passport_inputs(input_array):
    psp_array = []
    psp = ''
    for ipt in input_array:
        if(ipt == '\n' and psp != ''):
            psp_array.append(psp)
            psp = ''
        elif(ipt != "\n"):
            psp = psp + ipt
    psp_array.append(psp)
    return psp_array

if __name__ == '__main__':
    input_array = []
    with open('input.txt') as my_file:
        for line in my_file:
            input_array.append(line)
    psp_checker = passport_checker(get_array_of_passport_inputs(input_array))
    psp_checker.is_valid('iyr:2019\nhcl:#602927 eyr:1967 hgt:170cm\necl:grn pid:012533040 byr:1946')

    
        