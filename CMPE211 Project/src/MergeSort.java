public class MergeSort {
    

       public void merge(Applicant arr[], int l, int m, int r) { 
		
		int n1 = m - l + 1; //2
		int n2 = r - m; //1
             
		Applicant L[] = new Applicant [n1]; 
		Applicant R[] = new Applicant [n2]; 
		 
		// changed MergeSort a little bit. Because  have to sort an array                                                        
		for (int i=0; i<n1; ++i)               // which comprised of objects not just integers
			L[i] = arr[l + i]; 
		for (int j=0; j<n2; ++j) 
			R[j] = arr[m + 1+ j]; 	
	
		int i = 0, j = 0; 
		int k = l; 
                
		while (i < n1 && j < n2) { 
			if (L[i].result <= R[j].result){ 
				arr[k] = L[i]; 
				i++; 
			} 
			else{ 
				arr[k] = R[j]; 
				j++; 
			} 
			k++; 
		} 
		
		while (i < n1){ 
			arr[k] = L[i]; 
			i++; 
			k++; 
		} 

		while (j < n2){ 
			arr[k] = R[j]; 
			j++; 
			k++; 
		} 
	} 
     
     public void sort(Applicant arr[], int l, int r,int size){ 
		if (l < r){ 			
			int m = (l+r)/2; 		 
			sort(arr, l, m,size); 
			sort(arr , m+1, r,size);
			if(r==size)
				return;
			merge(arr, l, m, r); 
		} 
	}   
}
