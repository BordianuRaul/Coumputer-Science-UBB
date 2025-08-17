

def merge_sort(array: list, reverse: bool):

    if len(array) < 2:
        return array

    mid = len(array) // 2

    first_half = array[:mid]

    second_half = array[mid:]

    merge_sort(first_half, reverse)
    merge_sort(second_half, reverse)
    merge(first_half, second_half, array, reverse)

def merge(first_half: list, second_half: list, sorted_array: list, reverse: bool):

    len_1 = len(first_half)
    len_2 = len(second_half)
    aux_list = []

    i = 0
    j = 0

    while i < len_1 and j < len_2:

        if reverse == False:
            if first_half[i] < second_half[j]:
                aux_list.append(first_half[i])
                i += 1

            else:
                aux_list.append(second_half[j])
                j += 1
        else:
            if first_half[i] > second_half[j]:
                aux_list.append(first_half[i])
                i += 1

            else:
                aux_list.append(second_half[j])
                j += 1


    while i < len_1:
        aux_list.append(first_half[i])
        i += 1

    while j < len_2:
        aux_list.append(second_half[j])
        j += 1

    sorted_array.clear()
    sorted_array.extend(aux_list)


test_array = [1,3,2,5,7,12,32,123,32,22]

merge_sort(test_array, False)

print(test_array)
