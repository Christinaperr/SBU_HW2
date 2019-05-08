 let rec pow x n = match n with
  | 0 -> 1
  | 1 -> x
  | n -> x * pow x (n - 1);;
  
  
  let rec float_pow x n = match n with
  | 0.0 -> 1.0
  | 1.0 -> x
  | n -> x *. float_pow x (n -. 1.0);;
  
  
  let reverse list =
  let rec aux acc = function
  | [] -> acc
  | h::t -> aux (h::acc) t
  in aux [] list ;;
  
  
  let rec compress = function
    | h1 :: (h2 :: _ as t)-> if h1 = h2 then compress t else h1 :: compress t
    | x -> x;;
	

let cluster lst =
reverse(combine [] [] lst);;

let rec combine current combined = function
| [] -> []
| [x] -> (x :: current) :: combined
| h1 :: (h2 :: _ as t) -> if h1 = h2 then combine (h1 :: current) combined t
else combine [] ((h1 :: current) :: combined) t;;


let slice list i j =
    let rec first n = function
      | [] -> []
      | h :: t -> if n = 0 then [] else h :: first (n-1) t
    in
    let rec last n = function
      | [] -> []
      | h :: t as l -> if n = 0 then l else last (n-1) t
    in
    if j < i then [] else if List.length list < j then first(List.length list - i)(last i list) else first(j - i)(last i list)
;;



let composition f g = function
	x -> f(g x);;

  
let rec filter f g = function
  | [] -> []
  | h::t -> if f h = g h then h::(filter f g t) else [];;
  
	  
 let equiv_on f g lst = match filter f g lst with
    | [] -> false
    | _ -> true;;
	 
	  
	  
let rec pairwisefilter cmp lst = match lst with
	| [] -> []
	| [x] -> [x]
	| h :: x :: t -> cmp h x :: pairwisefilter cmp t;;
	

let rec polynomial lst = fun n -> match lst with
	| [] -> 0
	| (a, b) :: t -> a * pow n b + polynomial t n;;



type bool_expr =
| Lit of string
| Not of bool_expr
| And of bool_expr * bool_expr
| Or of bool_expr * bool_expr
;;



let rec truth_table a b expres = 
let rec true_false a val_a b val_b = function
| Lit(x) -> if x = a then val_a else val_b
| And(x1,x2) -> true_false a val_a b val_b x1 && true_false a val_a b val_b x2
| Or(x1, x2) -> true_false a val_a b val_b x1 || true_false a val_a b val_b x2
| Not x -> not(true_false a val_a b val_b x)
in
[(true, true, true_false a true b true expres);
(true, false, true_false a true b false expres);
(false, true, true_false a false b true expres);
(false, false, true_false a false b false expres);];;


type 'a binary_tree = 
| Node of 'a *  'a binary_tree *  'a binary_tree
| Empty
;;

let rec tree2str a = match a with
| Empty -> ""
| Node(x, Empty, Empty) -> (string_of_int x) 
| Node(x, y, Empty) -> string_of_int x ^ "(" ^ (tree2str y) ^ "," ^ (tree2str Empty) ^ ")"
| Node(x, Empty, z) -> string_of_int x ^ "(" ^ (tree2str Empty) ^ "," ^ (tree2str z) ^ ")"
| Node(x, y, z) -> (string_of_int x) ^ "("  ^ (tree2str y) ^ "," ^ (tree2str z) ^ ")"
;;





