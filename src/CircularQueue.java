class Node
{
	Node next;
	Page data;

	public Node()
	{ // Empty Constructor
	}

	public Node(Page data)
	{
		this.data = data;
	}

	public Node(Page data, Node next)
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

	public Page getData()
	{
		return this.data;
	}

	public void setData(Page data)
	{
		this.data = data;
	}
}

public class CircularQueue
{
	Node head;
	Node tail;

	public void enqueue(Page data)
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

	public Page dequeue()
	{
		if (head == null) return null;
		Page ret = head.getData();

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
