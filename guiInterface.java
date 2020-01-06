package javaSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.geom.*;
import java.util.List;
import java.awt.geom.RectangularShape;

import javax.swing.JTextArea;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

public class guiInterface {
	static int shapeChecker = 0;
	static int currentCard = 0;
	static int size = 0;
	static JLabel status;
	static ArrayList<ContentArea> canvasPages;
	static JPanel cardPanel;
	static Container contain;
	static JMenuItem previous;
	static JMenuItem delete;
	static int numofCanvas = 0;
	static JMenuItem next;
	static JToggleButton color1;
	static ContentArea page;
	static Color newColor;
	static ContentArea canvasArea;
	static boolean editMode = false;
	static int widthNumber = 0;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		// create a new JFrame, which is a top-level window
		JFrame frame = new JFrame("Canvas Painter");
		cardPanel = new JPanel();
		
		// $1 Gesture Recognizer
		DollarRecognizer recognition = new DollarRecognizer();
		cardPanel.setPreferredSize(new Dimension(800, 600));
		JScrollPane scrollPane = new JScrollPane((cardPanel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getViewport().setPreferredSize(new Dimension(900, 700));
//		cardLayout = new RXCardLayout();
//		cardPanel.setLayout(cardLayout);
		cardPanel.setLayout(new BorderLayout());
		//cardPanel.add(new ContentArea(shapeChecker), BorderLayout.CENTER);
		canvasPages = new ArrayList<>();
		ContentArea initialContent = new ContentArea(shapeChecker);
		canvasPages.add(initialContent);
		cardPanel.add(initialContent, BorderLayout.CENTER);
		
		
		canvasArea = canvasPages.get(numofCanvas);
		widthNumber = canvasArea.getWidth();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets up a menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// setus up the file aspect of the menu bar
		JMenu file = new JMenu("File");
		menuBar.add(file);
		// adds the items for the file aspect
		JMenuItem newButton = new JMenuItem("New");
		file.add(newButton);
		delete = new JMenuItem("Delete");
		file.add(delete);
		JMenuItem quitButton = new JMenuItem("Quit");
		file.add(quitButton);
		// when clicked it will exit out of the program
		quitButton.addActionListener(e -> System.exit(0));
		// sets up the edit aspect of the menu bar
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		// sets up the view aspect of the menu bar
		JMenu view = new JMenu("View");
		menuBar.add(view);
		// adds the aspects to the view part of the program.
		next = new JMenuItem("Next");
		view.add(next);
		previous = new JMenuItem("Previous");
		view.add(previous);
		// Left Hand Side tool pallette
		// if possible add the toggle buttons to a group
		JPanel buttons = new JPanel(new GridLayout(0, 1));
		ButtonGroup group = new ButtonGroup();
		JToggleButton select = new JToggleButton();
		select.setIcon(new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\selectbutton.png"));
		group.add(select);
		JToggleButton line = new JToggleButton();
		line.setIcon(new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\linebutton.png"));
		group.add(line);
		JToggleButton rectangle = new JToggleButton();
		rectangle.setIcon(
				new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\rectanglebutton.png"));
		group.add(rectangle);
		JToggleButton oval = new JToggleButton();
		oval.setIcon(new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\ovalbutton.png"));
		group.add(oval);
		JToggleButton pen = new JToggleButton();
		pen.setIcon(new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\penbutton.png"));
		group.add(pen);
		JToggleButton text = new JToggleButton();
		text.setIcon(new ImageIcon("C:\\Users\\HamzaHamid\\Desktop\\cs4470\\swing-examples\\HW1\\textbutton.png"));
		group.add(text);

		// adds the button to a JPanel called buttons
		buttons.add(select);
		buttons.add(line);
		buttons.add(rectangle);
		buttons.add(oval);
		buttons.add(pen);
		buttons.add(text);

		// main content area
		// Container content = frame.getContentPane();
		// content.setLayout(new BorderLayout());
		// final ContentArea canvas = new ContentArea(shapeChecker);
		// content.add(canvas, BorderLayout.CENTER);

		// status bar
		status = new JLabel();
		status.setText(" Status Bar");

		// This creates actions when each specific button is clicked.
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addGest();
			}
		});
		// should add a new canvas
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delGest();
			}
		});

		// should delete the current tab
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nextGest();
			}
		});
		// should go to the next tab
		previous.addActionListener(new ActionListener() {
			// if the list gets to the beginning, disable button
			public void actionPerformed(ActionEvent e) {
				prevGest();
			}
		});
		// should go to the previous tab
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Select button has been clicked");
				shapeChecker = 0;
			}
		});
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Line button has been clicked");
				shapeChecker = 1;
			}
		});
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Rectangle button has been clicked");
				shapeChecker = 2;
			}
		});
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Ellipse button has been clicked");
				shapeChecker = 3;
			}
		});
		pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Pen button has been clicked");
				shapeChecker = 4;
			}
		});
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(" Text button has been clicked");
				shapeChecker = 5;
			}
		});
		// Extra Credit
		// Color chooser
		color1 = new JToggleButton();
		color1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseColor();
			}
		});

		// this pretty much says that a color has been chosen
		color1.addActionListener(e -> status.setText(" Color 1 Button has been clicked"));
		// adds the color button to the group
		// which is then added to the buttons JPanel
		group.add(color1);
		buttons.add(color1);
		// creates a pannel of the buttons, and other things
		JPanel left = new JPanel(new BorderLayout());
		left.add(buttons, BorderLayout.NORTH);
		frame.add(left, BorderLayout.WEST);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.add(status, BorderLayout.SOUTH);
		frame.setSize(300, 300);

		// pack() causes the size of the frame to be set just large enough to contain
		// its
		// children; setVisible(true) puts it on the screen
		frame.revalidate();
		frame.pack();
		frame.setVisible(true);
	}

	protected static void addGest() {
		// TODO Auto-generated method stub
		status.setText(" New button has been clicked");
		// Essentially create a new Content Area
		// when new button is pressed each time
		canvasPages.add(new ContentArea(shapeChecker));
		numofCanvas = canvasPages.size() - 1;
		cardPanel.add(canvasPages.get(numofCanvas), BorderLayout.CENTER);
	}

	public static void nextGest() {
		if (numofCanvas != canvasPages.size() - 1) {
			canvasArea.pageFlipping(canvasPages.get(numofCanvas + 1) , true, 25);
			status.setText(" Next Canvas");
		}
	}

	public static void prevGest() {
		System.out.println(numofCanvas);
		System.out.println(canvasPages.size() - 1);
		if (numofCanvas > 0) {
			canvasArea.pageFlipping(canvasPages.get(numofCanvas - 1) , false, 25);
			status.setText(" Previous Canvas");
		}
	}
	public static void delGest() {
		status.setText(" Deleting Canvas");
		if (canvasPages.size() == 1) {
			
		} else if (numofCanvas == 0) {
			cardPanel.remove(canvasPages.get(numofCanvas));
			canvasPages.remove(numofCanvas);
		} else {
			cardPanel.remove(canvasPages.get(numofCanvas));
			canvasPages.remove(numofCanvas);
			numofCanvas = -1;
		}
		cardPanel.repaint();
		cardPanel.revalidate();
	}
	public static void chooseColor() {
		newColor = JColorChooser.showDialog(null, "Choose", color1.getBackground());
		if (newColor != null) {
			color1.setBackground(newColor);
		}
	}
}
// creates the content area and places it in the text area
class ContentArea extends JComponent {
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	int oldX = 0;
	int oldY = 0;
	int currentX = 0;
	int currentY = 0;
	int mpShapeX = 0;
	int mpShapeY = 0;
	int mpShapeX2 = 0;
	int mpShapeY2 = 0;
	Rectangle2D rect;
	int prevX = 0;
	int prevY = 0;
	int directionDiffX = 0;
	int directionDiffY = 0;
	int threshold = 20;
	int xDirection = 0;
	int yDirection = 0;
	int initialptX = 0;
	int initialptY = 0;

	Timer timer;
	BufferedImage offScreenImage;
	int flipPageX = 0;
	int iterations = 0;
	int flipPageY = 0;
	boolean flippingMode = false;
	boolean forwards;
	double dx;
	double dy;
	Point startPoint;
	Point prevPoint;
	ArrayList<Shape> variousShapes = new ArrayList<Shape>();
	ArrayList<Shape> shapestoDel = new ArrayList<Shape>();
	Shape theShape;
	Shape shapetoDrag;
	Shape clearer;
	boolean selected = false;
	boolean clear = false;
	boolean dragging = false;
	boolean goingNorth = false;
	boolean goingSouth = false;
	boolean goingEast = false;
	boolean goingWest = false;
	boolean snappedN = false;
	boolean snappedS = false;
	boolean snapped = false;
	boolean snappedE = false;
	boolean snappedW = false;
	boolean resizable = true;
	private int pos = -1;
	ArrayList<Point2D> points = null;
	private Point2D.Double offset;
	DollarRecognizer recognizer = new DollarRecognizer();

	public ContentArea(int shapeChecker) {
		int shapeNum = shapeChecker;
		// setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					// search through arraylist that finds a shape that contains the mouse
					// if found then selected variable will be true
					// save shape into a variable
					oldX = e.getX();
					oldY = e.getY();
					initialptX = e.getX();
					initialptY = e.getY();
					currentX = oldX;
					currentY = oldY;
					Point p = e.getPoint();
					startPoint = new Point(e.getX(), e.getY());
					if (guiInterface.shapeChecker == 0) {
						for (Shape mpShapes : variousShapes) {
							if (mpShapes.contains(startPoint)) {
								if (mpShapes instanceof Rectangle2D || mpShapes instanceof Ellipse2D
										|| mpShapes instanceof Line2D) {
									selected = true;
									shapetoDrag = mpShapes;
									offset = new Point2D.Double(oldX - shapetoDrag.getBounds2D().getX(),
											oldY - shapetoDrag.getBounds2D().getY());
								}
							}
						}
					}
				} else if (SwingUtilities.isRightMouseButton(e)){
					if (e.getX() < 10) {
						if (guiInterface.numofCanvas > 0) {
							flippingMode = true;
							forwards = false;
							offScreenImage = ImageRender.makeOffscreenImage(guiInterface.canvasPages.get(guiInterface.numofCanvas - 1));
							flipPageX = e.getX();
						}
					} else if (e.getX() > guiInterface.widthNumber - 10) {
						if (guiInterface.numofCanvas != guiInterface.canvasPages.size() - 1) {
							flippingMode = true;
							forwards = true;
							offScreenImage = ImageRender.makeOffscreenImage(guiInterface.canvasPages.get(guiInterface.numofCanvas + 1));
						}
					} else {
						//do nothing
					}
					repaint();
					revalidate();
				
				} else {
					points = new ArrayList<Point2D>();
					// translate to canvas coordinate system
					Point p = e.getPoint();
					points.add(p);
					repaint();
					revalidate();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					switch (guiInterface.shapeChecker) {
					case 1:
						theShape = drawLine(oldX, oldY, e.getX(), e.getY());
						break;
					case 2:
						theShape = drawRectangle(oldX, oldY, e.getX(), e.getY());
						break;
					case 3:
						theShape = drawEllipse(oldX, oldY, e.getX(), e.getY());
						break;
					case 5:
						selected = false;
						theShape = drawText(oldX, oldY, e.getX(), e.getY());
						JTextArea textArea = new JTextArea(" Write Something");
						textBounds(oldX, oldY, e.getX(), e.getY());
						textArea.setLineWrap(true);
						textArea.setWrapStyleWord(true);
						textArea.setBounds(x, y, width, height);
						add(textArea);
						// drawRect = false;
						break;
					default:
						theShape = null;
						break;

					}
					selected = false;
					startPoint = null;
					dragging = false;
					oldX = 0;
					oldY = 0;
					currentX = 0;
					currentY = 0;
					repaint();

				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (flippingMode) {
						if (forwards) {
							if (e.getX() > guiInterface.widthNumber / 2) {
								pageflipStops(e.getX());
							} else if (e.getX() < guiInterface.widthNumber / 2 && e.getX() > 40) {
								animationComplete(e.getX());
							} else {
								flippingMode = false;
			                    repaint();
			                    revalidate();
			                    guiInterface.numofCanvas += 1;
							}
						} else {
							if (e.getX() < (guiInterface.widthNumber / 2)) {
								pageflipStops(e.getX());
							} else if ((e.getX() > guiInterface.widthNumber / 2) && (e.getX() < guiInterface.widthNumber)) {
								animationComplete(e.getX());
							} else {
								flippingMode = false;
								repaint();
								revalidate();
								guiInterface.numofCanvas -=1;
							}
						}
					}	
				} else {
					Point p = e.getPoint();
					points.add(p);
					Result result = recognizer.recognize(points);
					updateStatus(result);
					if (result.getName().equals("rectangle")) {
						Rectangle rect = result.getBoundingBox();
						int rx1 = (int) rect.getX();
						int ry1 = (int) rect.getY();
						int rx2 = (int) (rect.getX() + rect.getWidth());
						int ry2 = (int) (rect.getY() + rect.getHeight());

						theShape = drawRectangle(rx1, ry1, rx2, ry2);
					} else if (result.getName().equals("circle")) {
						Rectangle ovalRect = result.getBoundingBox();
						int ox1 = (int) ovalRect.getX();
						int oy1 = (int) ovalRect.getY();
						int ox2 = (int) (ovalRect.getX() + ovalRect.getWidth());
						int oy2 = (int) (ovalRect.getY() + ovalRect.getHeight());

						theShape = drawEllipse(ox1, oy1, ox2, oy2);
					} else if (result.getName().equals("star")) {
						guiInterface.addGest();
					} else if (result.getName().equals("caret")) {
						// guiInterface.previous.setEnabled(true);
						// guiInterface.next.setEnabled(true);
						// //if the list gets to the end, disable button
						// guiInterface.status.setText(" Next button has been clicked");
						// //System.out.println("This is the" + size);
						// guiInterface.cardLayout.next(guiInterface.cardPanel);
						// guiInterface.next.setEnabled(guiInterface.cardLayout.isNextCardAvailable());
						guiInterface.nextGest();
					} else if (result.getName().equals("v")) {
						// guiInterface.next.setEnabled(true);
						// guiInterface.previous.setEnabled(true);
						// guiInterface.status.setText(" Previous button has been clicked");
						// guiInterface.cardLayout.previous(guiInterface.cardPanel);
						// guiInterface.previous.setEnabled(guiInterface.cardLayout.isPreviousCardAvailable());
						guiInterface.prevGest();
					} else if (result.getName().equals("delete")) {
						// if (guiInterface.cardLayout.isPreviousCardAvailable()) {
						// guiInterface.status.setText(" Delete button has been clicked");
						// guiInterface.cardPanel.remove(guiInterface.cardLayout.getCurrentCard());
						// }
						guiInterface.delGest();
					} else if (result.getName().equals("x")) {
						// check if the gesture is intersecting with an object
						// if it does intersect erase whatever hits the bounding
						// box
						Rectangle rectBox = result.getBoundingBox();
						for (Shape mpShapes : variousShapes) {
							if ((mpShapes.getBounds2D().intersects(rectBox))
									|| mpShapes.getBounds2D().contains(rectBox.getX(), rectBox.getY())) {
								clear = true;
								variousShapes.remove(mpShapes);
								theShape = null;

							}
						}

					} else if (result.getName().equals("pigtail")) {
						guiInterface.chooseColor();

					} 
				
				repaint();
				revalidate();
				}
				if (theShape != null) {
					variousShapes.add(theShape);
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// check if selected shape, if so, then adjust the shapes
				// location and repaint.
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (selected) {
						double diffX = e.getX() - offset.getX();
						double diffY = e.getY() - offset.getY();
						if ((e.getX() == (shapetoDrag.getBounds2D().getX() + shapetoDrag.getBounds2D().getWidth()))
								&& (e.getX() == (shapetoDrag.getBounds2D().getY()
										+ shapetoDrag.getBounds2D().getHeight()))) {
							resizable = true;
						}
						translateShape(shapetoDrag, diffX, diffY);
						if (resizable) {
							int dx = e.getX() - initialptX;
							int dy = e.getY() - initialptY;
							resizeShape(shapetoDrag, dx, dy);
						}
						prevX = oldX;
						prevY = oldY;
						xDirection = e.getX();
						yDirection = e.getY();

						if (dragging) {
							// if dragging north
							if (((yDirection - prevY) < 0)
									&& (Math.abs(yDirection - prevY) > Math.abs(xDirection - prevX))) {
								if (snappedN || snappedS) {
									// if distance of mouse and shape + grab exceeds
									// the threshold (then do nothing)
									if (Math.abs(e.getX() - shapetoDrag.getBounds2D().getY() + prevX) < threshold) {
										// user is dragging but not enough
									} else {
										shapetoDrag.getBounds2D().setFrame(shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY() - 1,
												shapetoDrag.getBounds2D().getWidth(),
												shapetoDrag.getBounds2D().getHeight());
										prevY = (int) (e.getY() - shapetoDrag.getBounds2D().getY());
									}
									// else the object will be unsnapped
									// the shapes y will be shapes y - 1
									// the grabs y will be mouse y - shape y
								} else {
									// not snapped and check to see if within threshold
									// essentially check if the shape is within 25 spaces from the
									// the gridline above
									if ((shapetoDrag.getBounds2D().getY() % 100 < threshold)) {
										// if the below statement is true,
										// update shape.y = grid position (100); GRAB.Y = EV.Y - SHAPE.Y
										// if (shapetoDrag.getBounds2D().getY() < threshold) {
										translateShape(shapetoDrag, shapetoDrag.getBounds().getX(),
												shapetoDrag.getBounds2D().getY()
														- (shapetoDrag.getBounds2D().getY() % 100));
										prevY = (int) (e.getY() - shapetoDrag.getBounds2D().getY());
										guiInterface.status.setText("Shape TOP-EDGE Snapped");
										// snappedN = true;
										// move the shape up normally until it gets to a threshold
									} else {
										translateShape(shapetoDrag, shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY());
									}
								}
								// dragging south
							} else if (((yDirection - prevY) > 0)
									&& (Math.abs(yDirection - prevY) > Math.abs(xDirection - prevX))) {
								if (snappedN || snappedS) {
									// if distance of mouse and shape + grab exceeds
									// the threshold (then do nothing)
									if (Math.abs(e.getY() - shapetoDrag.getBounds2D().getY() + prevX) < threshold) {
										// user is dragging but not enough
									} else {
										shapetoDrag.getBounds2D().setFrame(shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY() - 1,
												shapetoDrag.getBounds2D().getWidth(),
												shapetoDrag.getBounds2D().getHeight());
										prevY = (int) (e.getY() - shapetoDrag.getBounds2D().getY());
									}
									// else the object will be unsnapped
									// the shapes y will be shapes y - 1
									// the grabs y will be mouse y - shape y
								} else {
									int num = (int) shapetoDrag.getBounds2D().getHeight();
									// not snapped and check to see if within threshold
									// essentially check if the shape is within 25 spaces from the
									// the gridline above
									int firstdig = firstDigit(num);
									if (((shapetoDrag.getBounds2D().getY() + shapetoDrag.getBounds2D().getHeight())
											% 100 > 100 - threshold)) {
										// System.out.println(shapetoDrag.getBounds2D().getHeight());
										// if the below statement is true,
										// update shape.y = grid position (100); GRAB.Y = EV.Y - SHAPE.Y
										// if (shapetoDrag.getBounds2D().getY() < threshold) {
										if (num < 100) {
											translateShape(shapetoDrag, shapetoDrag.getBounds().getX(),
													((shapetoDrag.getBounds2D().getY()
															- (shapetoDrag.getBounds2D().getY() % 100)) + (100))
															- shapetoDrag.getBounds2D().getHeight());
										} else {
											translateShape(shapetoDrag, shapetoDrag.getBounds().getX(),
													((shapetoDrag.getBounds2D().getY()
															- (shapetoDrag.getBounds2D().getY() % 100))
															+ ((100) + (100 * firstdig)))
															- shapetoDrag.getBounds2D().getHeight());
										}
										prevY = (int) (e.getY() - shapetoDrag.getBounds2D().getY());
										guiInterface.status.setText("Shape BOTTOM-EDGE Snapped");
										// snappedN = true;
										// move the shape up normally until it gets to a threshold
									} else {
										translateShape(shapetoDrag, shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY());
									}
								}
								// GOING WEST
							} else if (((xDirection - prevX) > 0)
									&& (Math.abs(yDirection - prevY) < Math.abs(xDirection - prevX))) {
								if (snappedE || snappedW) {
									// if distance of mouse and shape + grab exceeds
									// the threshold (then do nothing)
									if (Math.abs(e.getX() - shapetoDrag.getBounds2D().getX() + prevX) < threshold) {
										// user is dragging but not enough
									} else {
										shapetoDrag.getBounds2D().setFrame(shapetoDrag.getBounds2D().getX() - 1,
												shapetoDrag.getBounds2D().getY(), shapetoDrag.getBounds2D().getWidth(),
												shapetoDrag.getBounds2D().getHeight());
										prevX = (int) (e.getX() - shapetoDrag.getBounds2D().getX());
									}
									// else the object will be unsnapped
									// the shapes y will be shapes y - 1
									// the grabs y will be mouse y - shape y
								} else {
									// not snapped and check to see if within threshold
									// essentially check if the shape is within 25 spaces from the
									// the gridline above
									if ((shapetoDrag.getBounds2D().getX() % 100 < threshold)) {
										// if the below statement is true,
										// update shape.y = grid position (100); GRAB.Y = EV.Y - SHAPE.Y
										// if (shapetoDrag.getBounds2D().getY() < threshold) {
										translateShape(shapetoDrag,
												shapetoDrag.getBounds2D().getX()
														- (shapetoDrag.getBounds2D().getX() % 100),
												shapetoDrag.getBounds2D().getY());
										prevX = (int) (e.getX() - shapetoDrag.getBounds2D().getX());
										guiInterface.status.setText("Shape LEFT-EDGE Snapped");
										// snappedN = true;
										// move the shape up normally until it gets to a threshold
									} else {
										translateShape(shapetoDrag, shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY());
									}
								}

								// GOING EAST
							} else if (((xDirection - prevX) < 0)
									&& (Math.abs(yDirection - prevY) < Math.abs(xDirection - prevX))) {
								if (snappedE || snappedW) {
									// if distance of mouse and shape + grab exceeds
									// the threshold (then do nothing)
									if (Math.abs(e.getX() - shapetoDrag.getBounds2D().getX() + prevX) < threshold) {
										// user is dragging but not enough
									} else {
										shapetoDrag.getBounds2D().setFrame(shapetoDrag.getBounds2D().getX() - 1,
												shapetoDrag.getBounds2D().getY(), shapetoDrag.getBounds2D().getWidth(),
												shapetoDrag.getBounds2D().getHeight());
										prevX = (int) (e.getX() - shapetoDrag.getBounds2D().getX());
									}
									// else the object will be unsnapped
									// the shapes y will be shapes y - 1
									// the grabs y will be mouse y - shape y
								} else {
									int num = (int) shapetoDrag.getBounds2D().getWidth();
									// not snapped and check to see if within threshold
									// essentially check if the shape is within 25 spaces from the
									// the gridline above
									int firstdig = firstDigit(num);
									if (((shapetoDrag.getBounds2D().getX() + shapetoDrag.getBounds2D().getWidth())
											% 100 > 100 - threshold)) {
										// System.out.println(shapetoDrag.getBounds2D().getHeight());
										// if the below statement is true,
										// update shape.y = grid position (100); GRAB.Y = EV.Y - SHAPE.Y
										// if (shapetoDrag.getBounds2D().getY() < threshold) {
										if (num < 100) {
											translateShape(shapetoDrag,
													((shapetoDrag.getBounds2D().getX()
															- (shapetoDrag.getBounds2D().getX() % 100)) + (100))
															- shapetoDrag.getBounds2D().getWidth(),
													shapetoDrag.getBounds().getY());
										} else {
											translateShape(shapetoDrag,
													((shapetoDrag.getBounds2D().getX()
															- (shapetoDrag.getBounds2D().getX() % 100))
															+ ((100) + (100 * firstdig)))
															- shapetoDrag.getBounds2D().getWidth(),
													shapetoDrag.getBounds().getY());
										}
										prevX = (int) (e.getX() - shapetoDrag.getBounds2D().getX());
										guiInterface.status.setText("Shape RIGHT-EDGE Snapped");
										// snappedN = true;
										// move the shape up normally until it gets to a threshold
									} else {
										translateShape(shapetoDrag, shapetoDrag.getBounds2D().getX(),
												shapetoDrag.getBounds2D().getY());
									}
								}
							}

						}
						repaint();
					}
					// in this case, the drawing points should be recognized
					if (guiInterface.shapeChecker == 4) {
						selected = false;
						variousShapes.add(drawLine(oldX, oldY, e.getX(), e.getY()));
						oldX = e.getX();
						oldY = e.getY();
						repaint();
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					if (flippingMode) {
						if (e.getX() > 0 && e.getX() < guiInterface.widthNumber) {
							flipPageX = e.getX();
						}
					}
					repaint();
					revalidate();
				} else {
					Point p = e.getPoint();
					points.add(p);
					repaint();
					
				}
			}
		});
	}

	private void translateShape(Shape a, double xTranslate, double yTranslate) {
		if (a instanceof Ellipse2D) {
			Rectangle2D shapeBound = a.getBounds2D();
			shapeBound.setFrame(
					new Rectangle2D.Double(xTranslate, yTranslate, shapeBound.getWidth(), shapeBound.getHeight()));
			((Ellipse2D) a).setFrame(shapeBound);
			guiInterface.status.setText(" Ellipse is Moving!");
		} else if (a instanceof Rectangle2D) {
			Rectangle2D shapeBound = (Rectangle2D) a;
			shapeBound.setFrame(xTranslate, yTranslate, shapeBound.getWidth(), shapeBound.getHeight());
			guiInterface.status.setText(" Rectangle is Moving!");
		} else {
			Line2D line = (Line2D) a;
			// line.setLine(line.getX1() + xTranslate, line.getY1() + yTranslate,
			// line.getX2() + xTranslate, y2 + );
		}
		dragging = true;
	}

	private void resizeShape(Shape a, int dx, int dy) {
		Rectangle2D shapeBound = (Rectangle2D) a;
		double newWidth = shapeBound.getBounds2D().getWidth() + dx;
		double newHeight = shapeBound.getBounds2D().getHeight() + dy;
		// shapeBound.setFrame(dx, dy, newWidth, newHeight);
	}
	private static int firstDigit(int num) {
		while (num >= 10) {
			num = num / 10;
		}
		return num;
	}
	public void pageFlipping(ContentArea change, boolean movingForwards, int n) {
		offScreenImage = ImageRender.makeOffscreenImage(change);
		flippingMode = true;
		this.forwards = movingForwards;
		timer = new Timer(75, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (iterations < n) {
					if (forwards) {
						flipPageX = change.getWidth() - (iterations + 1) * (change.getWidth()) / n;
					} else {
						flipPageX = (iterations) * (change.getWidth() / n);
					}
					flipPageY = 0;
					repaint();
					revalidate();
					iterations++;
				} else {
					timer.stop();
					flippingMode = true;
					iterations = 0;
					repaint();
					revalidate();
					if (forwards) {
						guiInterface.numofCanvas += 1;
					} else {
						guiInterface.numofCanvas -= 1;
					}
					guiInterface.cardPanel.revalidate();
					guiInterface.cardPanel.repaint();
				}
			}
		});
		timer.start();
	}
	public void pageflipStops(int a) {
		iterations = 0;
		int goingForwards = offScreenImage.getWidth() - a;
		timer = new Timer(75, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (iterations < 12) {
					if (!forwards) {
						flipPageX = a - (iterations + 1) * (a / 12);
					} else {
						flipPageX = a + (iterations) * (goingForwards/12);
					}
					repaint();
					revalidate();
					iterations++;
				} else {
					timer.stop();
					flippingMode = false;
					iterations = 0;
					repaint();
					revalidate();
				}
				guiInterface.cardPanel.revalidate();
				guiInterface.cardPanel.repaint();
			}
		});
		timer.start();
	}
	public void animationComplete(int a) {
		iterations = 0;
		int goingForwards = offScreenImage.getWidth() - a;
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (iterations < 12) {
					if (forwards) {
						flipPageX = a - (iterations + 1) * (a / 12);
					} else {
						flipPageX = a + (iterations) * (goingForwards / 12);
					}
					repaint();
					revalidate();
					iterations++;
				} else {
					timer.stop();
					flippingMode = false;
					iterations = 0;
					repaint();
					revalidate();
				}
				if (forwards) {
					guiInterface.numofCanvas += 1;
				} else {
					guiInterface.numofCanvas -= 1;
				}
				guiInterface.cardPanel.revalidate();
				guiInterface.cardPanel.repaint();
			}
		});
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (dragging) {
			Graphics2D g2d = (Graphics2D) g.create();
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
			g2d.setStroke(dashed);
			g2d.setColor(Color.GRAY);
			for (int x = 100; x < getWidth(); x += 100)
				g2d.drawLine(x, 0, x, getHeight());
			for (int y = 100; y < getHeight(); y += 100)
				g2d.drawLine(0, y, getWidth(), y);
		}

		Graphics2D graphics1 = (Graphics2D) g;
		Graphics2D graphics2 = (Graphics2D) g;
		Graphics2D graphics3 = (Graphics2D) g;
		ArrayList<Point2D> points = getPoints();

		graphics1.setStroke(new BasicStroke(4));
		graphics1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics1.setColor(guiInterface.newColor);
		for (Shape s : variousShapes) {
			graphics1.draw(s);
		}
		// gesture line
		if (points != null) {
			for (int i = 0; i < points.size() - 1; i++) {
				Point2D p1 = points.get(i);
				Point2D p2 = points.get(i + 1);
				graphics2.setStroke(new BasicStroke(3));
				graphics2.setPaint(Color.red);
				graphics2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics1.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
			}
		}
//		 check to see if page is flipping
//		 if so it draws it
		if (flippingMode) {
			if (forwards) {
                BufferedImage portion = offScreenImage.getSubimage(flipPageX, flipPageY, this.getWidth() - flipPageX,
						this.getHeight());
				graphics3.drawImage(portion, flipPageX, flipPageY, this);

			} else {

				if (flipPageX > 0) {
					BufferedImage portion = offScreenImage.getSubimage(0, 0, flipPageX,
							this.getHeight());
					graphics3.drawImage(portion, 0, 0, this);
				}
			}
			graphics3.drawRoundRect(flipPageX, flipPageY, 30, this.getHeight(), 5, 5);
            graphics3.setColor(new Color(238,238,238));
            graphics3.fillRoundRect(flipPageX, flipPageY, 30, this.getHeight(), 5, 5);
            graphics3.setColor(Color.white);
		}
	}
	@Override
	public void paintChildren(Graphics g) {
            super.paintChildren(g);
            int index = 0;
            for (Component comp: guiInterface.canvasPages) {
                if (index != guiInterface.numofCanvas) {
                    comp.setVisible(false);
                } else {
                    comp.setVisible(true);
                }
                index++;
        }
	}
	private Ellipse2D.Double drawEllipse(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x2 - x1);
		int height = Math.abs(y2 - y1);
		return new Ellipse2D.Double(x, y, width, height);
	}

	private Rectangle2D.Double drawRectangle(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x2 - x1);
		int height = Math.abs(y2 - y1);
		return new Rectangle2D.Double(x, y, width, height);
	}

	private Rectangle2D.Double drawText(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x2 - x1);
		int height = Math.abs(y2 - y1);
		return new Rectangle2D.Double(x, y, width, height);
	}

	private void textBounds(int x1, int y1, int x2, int y2) {
		x = Math.min(x1, x2);
		y = Math.min(y1, y2);
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
	}

	private Line2D.Double drawLine(int x1, int y1, int x2, int y2) {
		return new Line2D.Double(x1, y1, x2, y2);
	}

	public ArrayList<Point2D> getPoints() {
		return points;
	}

	private void updateStatus(Result r) {
		if (r == null) {
			guiInterface.status.setText("null result");
		} else if (r.getName().equals("No match")) {
			guiInterface.status.setText("No match");
		} else {
			Rectangle rect = r.getBoundingBox();
			guiInterface.status.setText(r.getName() + " (score=" + r.getScore() + ") " + " bbox: x=" + rect.getX()
					+ " y=" + rect.getY() + " w=" + rect.getWidth() + " h=" + rect.getHeight());
		}
	}


}


