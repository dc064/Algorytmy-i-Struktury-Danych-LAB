#include <stdio.h>

int main(int argc, char **argv) 
{
	int v[10] = {2, 3, 5, 6, 7, 8, 10, 14, 19, 20};
	int v_length = 10;
	int to_f = 8;

	printf("Liczba %d jest na %d pozycji", to_f, search(v, v_length, to_f) );
return 0;
}

int search (int *nums, int n, int to_find )
{
	int l=0, p=n, mid;

	while (l <= p)
	{
		mid = l + (p - l) / 2;
		if (nums[mid] == to_find)
			return mid;
		else if (nums[mid] > to_find)
			p = mid - 1;
		else
			l = mid + 1;
	}

	return -1;
}
