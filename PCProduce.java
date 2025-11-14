import java.util.Vector;

public class PCProduce {
   public static byte forgLearnLevelIndex = -1;
   public static int forgLearnTypeID = -1;
   public static int forgItemID = -1;
   public static byte forgLearnTopForm1 = 0;
   public static byte forgLearnTopForm2 = 0;
   public static int forgTopForm3 = 0;
   public static byte enchantForm1 = 0;
   public static byte enchantForm2 = 0;
   public static byte enchantForm3 = 0;
   public static String forgStr = "";
   public static byte selectedLevelIndex = -1;
   public static byte canListNum = 0;
   public static byte enchantingIndex = 0;
   public static byte enchantingRoute = 0;
   public static int skillLevel = 0;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2162689:
         MainCanvas.isWaiting = true;
      case 2162690:
      case 2162691:
      case 2162703:
      case 2162714:
      case 2162725:
      case 2162736:
      case 2162745:
      case 2162757:
      case 2162764:
      case 2162771:
      case 2162778:
      default:
         break;
      case 2162692:
      case 2162705:
      case 2162716:
      case 2162727:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162693:
      case 2162708:
      case 2162719:
      case 2162730:
      case 2162739:
      case 2162747:
      case 2162760:
      case 2162767:
      case 2162774:
      case 2162781:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162694:
      case 2162707:
      case 2162718:
      case 2162729:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         ba.writeInt(forgLearnTypeID);
         break;
      case 2162695:
      case 2162752:
         MainCanvas.isWaiting = true;
         ba.writeInt(forgItemID);
         break;
      case 2162696:
      case 2162709:
      case 2162720:
      case 2162731:
      case 2162740:
      case 2162748:
      case 2162761:
      case 2162768:
      case 2162775:
      case 2162782:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162697:
      case 2162710:
      case 2162721:
      case 2162732:
      case 2162741:
      case 2162749:
      case 2162762:
      case 2162769:
      case 2162776:
      case 2162783:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnTopForm1);
         ba.writeByte(forgLearnTopForm2);
         break;
      case 2162698:
      case 2162704:
      case 2162715:
      case 2162726:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162699:
      case 2162706:
      case 2162717:
      case 2162728:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         ba.writeInt(forgLearnTypeID);
         break;
      case 2162700:
      case 2162711:
      case 2162722:
      case 2162733:
      case 2162742:
         MainCanvas.isWaiting = true;
         ba.writeInt(forgItemID);
         break;
      case 2162701:
      case 2162712:
      case 2162723:
      case 2162734:
      case 2162743:
      case 2162751:
         MainCanvas.isWaiting = true;
         ba.writeInt(forgTopForm3);
         ba.writeByte(forgLearnLevelIndex);
         ba.writeInt(forgLearnTypeID);
         break;
      case 2162702:
         MainCanvas.isWaiting = true;
         break;
      case 2162713:
         MainCanvas.isWaiting = true;
         break;
      case 2162724:
         MainCanvas.isWaiting = true;
         break;
      case 2162735:
         MainCanvas.isWaiting = true;
         break;
      case 2162737:
      case 2162746:
      case 2162758:
      case 2162765:
      case 2162772:
      case 2162779:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162738:
      case 2162759:
      case 2162766:
      case 2162773:
      case 2162780:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162744:
         MainCanvas.isWaiting = true;
         break;
      case 2162750:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         ba.writeInt(forgItemID);
         break;
      case 2162753:
         MainCanvas.isWaiting = true;
         ba.writeByte(forgLearnLevelIndex);
         break;
      case 2162754:
         MainCanvas.isWaiting = true;
         ba.writeByte(UIGrid.useIndex);
         ba.writeByte(enchantingRoute);
         ba.writeByte(enchantingIndex);
         break;
      case 2162755:
         MainCanvas.isWaiting = true;
         ba.writeByte(enchantForm1);
         ba.writeByte(enchantForm2);
         ba.writeByte(enchantForm3);
         break;
      case 2162756:
         MainCanvas.isWaiting = true;
         break;
      case 2162763:
         MainCanvas.isWaiting = true;
         break;
      case 2162770:
         MainCanvas.isWaiting = true;
         break;
      case 2162777:
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145320958:
         skillLevel = data.readInt();
         UILabel skillLabel;
         UIForm form2;
         if (MainCanvas.curForm.clientCommand != 2162699 && MainCanvas.curForm.clientCommand != 2162706 && MainCanvas.curForm.clientCommand != 2162717 && MainCanvas.curForm.clientCommand != 2162728) {
            if (MainCanvas.curForm.clientCommand == 2162737 || MainCanvas.curForm.clientCommand == 2162746 || MainCanvas.curForm.clientCommand == 2162758 || MainCanvas.curForm.clientCommand == 2162759 || MainCanvas.curForm.clientCommand == 2162765 || MainCanvas.curForm.clientCommand == 2162766 || MainCanvas.curForm.clientCommand == 2162772 || MainCanvas.curForm.clientCommand == 2162773 || MainCanvas.curForm.clientCommand == 2162779 || MainCanvas.curForm.clientCommand == 2162780) {
               skillLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
               skillLabel.setNum1(skillLevel);
               Vector v = (Vector)MainCanvas.backForms.elementAt(MainCanvas.backForms.size() - 1);
               UIForm form1 = (UIForm)v.elementAt(0);
               if (form1 != null) {
                  skillLabel = (UILabel)form1.getComponents().elementAt(7);
                  skillLabel.setNum1(skillLevel);
               }

               form2 = (UIForm)v.elementAt(1);
               if (form2 != null) {
                  skillLabel = (UILabel)form2.getComponents().elementAt(7);
                  skillLabel.setNum1(skillLevel);
               }
            }
         } else {
            skillLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
            skillLabel.setNum1(skillLevel);
            UIForm form1 = (UIForm)MainCanvas.backForms.elementAt(MainCanvas.backForms.size() - 1);
            if (form1 != null) {
               skillLabel = (UILabel)form1.getComponents().elementAt(5);
               skillLabel.setNum1(skillLevel);
            }

            Vector v = (Vector)MainCanvas.backForms.elementAt(MainCanvas.backForms.size() - 2);
            form2 = (UIForm)v.elementAt(0);
            if (form2 != null) {
               skillLabel = (UILabel)form2.getComponents().elementAt(7);
               skillLabel.setNum1(skillLevel);
            }

            UIForm form3 = (UIForm)v.elementAt(1);
            if (form3 != null) {
               skillLabel = (UILabel)form3.getComponents().elementAt(7);
               skillLabel.setNum1(skillLevel);
            }
         }
         break;
      case -2145320957:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162689, 2162691});
         break;
      case -2145320956:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162702, 2162703});
         break;
      case -2145320955:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162713, 2162714});
         break;
      case -2145320954:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162724, 2162725});
         break;
      case -2145320953:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162735, 2162736});
         break;
      case -2145320952:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162744, 2162745});
         break;
      case -2145320951:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162756, 2162757});
         break;
      case -2145320950:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162763, 2162764});
         break;
      case -2145320949:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162770, 2162771});
         break;
      case -2145320948:
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         MainCanvas.ni.sendCommands(new int[]{2162777, 2162778});
      }

   }
}
