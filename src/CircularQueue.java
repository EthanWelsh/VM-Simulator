class Node
{
	Node next;
	int data;

	public Node()
	{ // Empty Constructor
	}

	public Node(int data)
	{
		this.data = data;
	}

	public Node(int data, Node next)
	{
		this.data = data;
		this.next = next;
	}

	public void setNext(Node next)
	{
		this.next = next;
	}

	public Node getNext()
	{
		return this.next;
	}

	public int getData()
	{
		return this.data;
	}

	public void setData(int data)
	{
		this.data = data;
	}
}

public class CircularQueue
{
	Node head;
	Node tail;

	public void enqueue(int data)
	{
		Node n = new Node(data);
		if (head == null)
		{
			head = n;
			tail = n;
		}
		else
		{
			n.setNext(head);
			head = n;

			tail.setNext(n);
		}
	}

	public int dequeue()
	{
		if (head == null) return -1;
		int ret = head.getData();

		if(head.getNext() == null)
		{
			head = null;
			tail = null;
		}
		else
		{
			head = head.getNext();
			tail.setNext(head);
		}

		return ret;
	}

	public void shiftHead()
	{
		if(head.getNext() != null) head = head.getNext();
	}

	public String toString()
	{
		if(head == null || tail == null) return "";

		Node current = head;
		do
		{
			System.out.println(current.getData());
			current = current.getNext();
		} while (current != head);
		return "";
	}

}
