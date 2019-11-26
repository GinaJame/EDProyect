
public class Avl<T extends Comparable<T>> {
	private Nodo<T> raiz;

	public Nodo<T> getRaiz() {
		return raiz;
	}
	
	
	public void insertarElemento(T elemento) {
		raiz= insertaRecursivo(elemento,raiz);
	}
	
	
	public Nodo<T> insertaRecursivo(T elemento,Nodo<T> raiz) {
		if(raiz==null) {
			raiz= new Nodo<T>(elemento);
		}else {
			if(elemento.compareTo(raiz.getElemento())>0) {
				raiz.setDerecha(insertaRecursivo(elemento, raiz.getDerecha()));
				Balancear(raiz,elemento);
			}
			if(elemento.compareTo(raiz.getElemento())<0) {
				raiz.setIzquierda(insertaRecursivo(elemento, raiz.getIzquierda()));
				if(altura(raiz.getIzquierda())-altura(raiz.getDerecha())==2) {
					if(elemento.compareTo(raiz.getIzquierda().getElemento())<0) {
						raiz= rotaSimpleALaDerecha(raiz);
					}else {
						raiz= rotarDobleALaDerecha(raiz);
					}
				}	
			}
		}
		
		int altura= max(altura (raiz.getIzquierda()),altura(raiz.getDerecha()));
		System.out.println("Altura nodo: "+raiz.getElemento()+" "+altura);
		raiz.setAltura(altura+1);
		return raiz;
	}
	
	private Nodo<T> rotaSimpleALaIzquierda(Nodo<T> raiz){
		Nodo<T> temp= raiz.getDerecha();
		raiz.setDerecha(temp.getIzquierda());
		temp.setIzquierda(raiz);
		raiz.setAltura(max(altura(raiz.getIzquierda()),altura(raiz.getDerecha()))+1);
		temp.setAltura(max(altura(temp.getDerecha()),altura(raiz))+1);
		return temp;
	}
	private Nodo<T> rotaSimpleALaDerecha(Nodo<T> raiz){
		Nodo<T> temp= raiz.getIzquierda();
		raiz.setIzquierda(temp.getDerecha());
		temp.setDerecha(raiz);
		raiz.setAltura(max(altura(raiz.getIzquierda()),altura(raiz.getDerecha()))+1);
		temp.setAltura(max(altura(temp.getIzquierda()),altura(raiz))+1);
		return temp;
	}	
	
	private Nodo<T> rotarDobleALaIzquierda(Nodo<T> raiz){
		raiz.setDerecha(rotaSimpleALaDerecha(raiz.getDerecha()));
		return rotaSimpleALaIzquierda(raiz);
	}
	private Nodo<T> rotarDobleALaDerecha(Nodo<T> raiz){
		raiz.setIzquierda(rotaSimpleALaIzquierda(raiz.getIzquierda()));
		return rotaSimpleALaDerecha(raiz);
	}
	public void borrar(T elemento) {
        if (raiz == null) {
			System.out.println("Nada");
        }
        // Locate the node to be deleted and also locate its parent node
        Nodo<T> padre = null;
        Nodo<T> actual = raiz;
        while (actual != null) {
            if (elemento.compareTo(actual.getElemento()) < 0) {
                padre = actual;
                actual = actual.getIzquierda();
            } else if (elemento.compareTo(actual.getElemento()) > 0) {
                padre = actual;
                actual= actual.getDerecha();
            } else {
                break;
            }
        }

        if (actual == null) {
            System.out.println("NO ESTA EN EL ARBOL");
        }
        if (actual.getIzquierda() == null) {
            if (padre == null) {
                raiz = actual.getDerecha();
            } else {
                if (elemento.compareTo(padre.getElemento()) < 0) {
                    padre.setIzquierda(actual.getDerecha());
                } else {
                    padre.setDerecha(actual.getDerecha());
                }
            }
            Balancear(padre, padre.getElemento());
        } else {
            /**
             * Case 2: The current node has a left child. Locate the rightmost
             * node in the left subtree of the current node and also its parent
             */
            Nodo<T> padreDerecha = actual;
            Nodo<T> DerechaMost = actual.getIzquierda();

            while (DerechaMost.getDerecha() != null) {
                padreDerecha = DerechaMost;
                DerechaMost = DerechaMost.getDerecha(); // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            actual.setElemento(DerechaMost.getElemento());

            // Eliminate rightmost node
            if (padreDerecha.getDerecha() == DerechaMost) {
                padreDerecha.setDerecha(DerechaMost.getIzquierda());
            } else // Special case: parentOfRightMost is current
            {
                padreDerecha.setIzquierda(DerechaMost.getIzquierda());
            }

            Balancear(padreDerecha, padreDerecha.getElemento());
		}
	}
	public int max(int a, int b) {
		if(a>b) {
			return a;
		}else {
			return b;
		}
	}
	
    public int altura(Nodo<T> nodo) {
		if(nodo==null) {
			return -1;
		}else {
			return nodo.getAltura();
		}
	}
	public void clear() {
        raiz = null;
    }
	public void Balancear(Nodo<T> raiz, T elemento){
			if(altura(raiz.getIzquierda())-altura(raiz.getDerecha())==-2) {
				if(elemento.compareTo(raiz.getDerecha().getElemento())>0) {
					raiz= rotaSimpleALaIzquierda(raiz);
				}else {
					raiz= rotarDobleALaIzquierda(raiz);
				}
			}
			if(altura(raiz.getIzquierda())-altura(raiz.getDerecha())==2) {
				if(elemento.compareTo(raiz.getIzquierda().getElemento())<0) {
					raiz= rotaSimpleALaDerecha(raiz);
				}else {
					raiz= rotarDobleALaDerecha(raiz);
				}
			}	
	}
}