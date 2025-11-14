import java.util.Vector;

public class GOManager {
   public static boolean isShowOtherPlayer;
   public static boolean isShowName;
   public static boolean isShowMonsterName;
   private static GOManager instance = null;
   static GameObject currentTarget = null;
   static Special selectCircle = null;
   public static GameObject[] netGameObj = new GameObject[0];
   public static GameObject[] curGameObj = new GameObject[0];
   public byte testPlayerPing = 0;
   long sendPingTime = 0L;
   long reciPingTime = 0L;
   static short objx = 0;
   static short objy = 0;
   static int[] roleID = new int[3];
   static int reportedPlayerID;
   public static byte m_bBattlefieldMenuId = 3;
   public static boolean m_bPlayersortNPC;
   public static int FlagGain;
   public static short m_sBattlefieldIndex = 0;
   public static short m_bBattleDatatrans;

   public static GOManager getInstance() {
      if (instance == null) {
         instance = new GOManager();
      }

      return instance;
   }

   private GOManager() {
   }

   public void tick() {
      curGameObj = netGameObj;
      if (currentTarget == null || Player.getInstance().currentTargetIsFar()) {
         currentTarget = Player.getInstance();
         selectCircle.setSpecial(currentTarget, (short)0, (byte)-1);
         if (UIMenu.otherPlayerMenu != null) {
            UIMenu.otherPlayerMenu = null;
            MainCanvas.setGameState((byte)0);
         }
      }

      for(int i = curGameObj.length - 1; i >= 0; --i) {
         curGameObj[i].tick();
         curGameObj[i].setDrawID(-1);
      }

   }

   public static void addObj(GameObject obj) {
      if (obj != null) {
         if (Util.getArrayIndex(netGameObj, obj) == -1) {
            netGameObj = Util.addArray(netGameObj, obj);
         }

      }
   }

   public static void delObj(int objID) {
      int tmpIndex = getObjIndexFromList(objID);
      if (tmpIndex != -1) {
         if (netGameObj[tmpIndex] == currentTarget) {
            currentTarget = null;
            if (Player.getInstance().state == 102) {
               Player.getInstance().setState((byte)0);
            }
         }

         if (netGameObj[tmpIndex].type == 1) {
            OtherPlayer tmpPlayer = (OtherPlayer)netGameObj[tmpIndex];
            netGameObj = Util.removeArray(netGameObj, tmpIndex);
            delObj(tmpPlayer.cartPlayer);
         } else {
            netGameObj = Util.removeArray(netGameObj, tmpIndex);
         }

      }
   }

   public static void delObj(GameObject obj) {
      if (obj != null) {
         int tmpIndex = Util.getArrayIndex(netGameObj, obj);
         if (tmpIndex != -1) {
            if (netGameObj[tmpIndex] == currentTarget) {
               currentTarget = null;
            }

            netGameObj = Util.removeArray(netGameObj, tmpIndex);
         }
      }
   }

   public static void delObjs(int offset, int length) {
      netGameObj = Util.resizeArray(netGameObj, offset, -length);
   }

   public static void removeAll() {
      currentTarget = null;
      netGameObj = new GameObject[0];
      curGameObj = new GameObject[0];
   }

   public static GameObject getObj(int objID) {
      getInstance();
      int tmpId = getObjIndexFromList(objID);
      return tmpId == -1 ? null : netGameObj[tmpId];
   }

   private static int getObjIndexFromList(int objID) {
      for(int i = netGameObj.length - 1; i >= 0; --i) {
         if (objID == netGameObj[i].getID()) {
            return i;
         }
      }

      return -1;
   }

   public static void setCurrentTarget(GameObject obj) {
      if (obj != null) {
         currentTarget = obj;
      }
   }

   public static GameObject getCurrentTarget() {
      return currentTarget;
   }

   public byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 196612:
         ba.writeShort(Player.getInstance().x);
         ba.writeShort(Player.getInstance().y);
         break;
      case 196614:
         ba.writeShort(Player.getInstance().getSkill_ID());
         ba.writeInt(Player.getInstance().skill_OBJID);
         Player.getInstance().setSkill_ID((short)0);
      case 196621:
      default:
         break;
      case 196626:
         ba.writeInt(currentTarget.getID());
         break;
      case 196627:
      case 196628:
         ba.writeInt(Player.getInstance().playerDuel);
         break;
      case 196630:
         ba.writeInt(Player.busyId);
         break;
      case 196632:
         ba.writeByte(MainCanvas.picQuaInd);
         break;
      case 196633:
         SIManager.getInstance();
         ba.writeUTF(SIManager.shortCut.enShortCutStr());
         break;
      case 196634:
         int[] goods = SIManager.shortCut.enGoods();
         int length = goods.length;
         ba.writeByte(length);

         for(int i = 0; i < length; ++i) {
            ba.writeInt(goods[i]);
         }

         return ba.toByteArray();
      case 196635:
         ba.writeBoolean(MainCanvas.pvpSetup == 0);
         break;
      case 196637:
         ba.writeByte(MainCanvas.quantitySetup);
         break;
      case 196638:
         ba.writeInt(reportedPlayerID);
         break;
      case 196639:
         ba.writeByte(this.testPlayerPing);
         if (this.reciPingTime < 0L) {
            ba.writeInt(120000);
         } else {
            ba.writeInt((int)(this.reciPingTime - this.sendPingTime));
         }

         ++this.testPlayerPing;
         this.sendPingTime = System.currentTimeMillis();
         this.reciPingTime = -1L;
         break;
      case 196641:
         MainCanvas.isWaiting = true;
         ba.writeLong(GameObject.m_lBattlefield);
         break;
      case 196642:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bBattlefieldMenuId);
         break;
      case 851969:
         MainCanvas.isWaiting = true;
         ba.writeInt(currentTarget.getID());
         break;
      case 851974:
         MainCanvas.isWaiting = true;
         ba.writeInt(currentTarget.getID());
         break;
      case 851975:
         MainCanvas.isWaiting = true;
         ba.writeInt(currentTarget.getID());
         ba.writeByte(Util.m_nTalkId);
         break;
      case 851976:
         MainCanvas.isWaiting = true;
         break;
      case 851978:
         ba.writeInt(PCPackage.botianID);
         break;
      case 851988:
      case 851989:
      case 852018:
      case 852019:
      case 852033:
      case 852034:
      case 852035:
      case 852036:
         if (commID == 852018) {
            if (MainCanvas.curForm != null) {
               MainCanvas.curForm = null;
            }

            if (MainCanvas.rightMenu != null) {
               MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
               MainCanvas.rightMenu = null;
               MainCanvas.setGameState((byte)0);
            }
         }

         ba.writeShort(m_bBattleDatatrans);
         break;
      case 851992:
         Player.getInstance().setAttacked(false);
         Player.getInstance().isGettingFlag = 1;
         Player.getInstance().setState((byte)3);
         ba.writeInt(currentTarget.getID());
         break;
      case 851993:
      case 852009:
         ba.writeInt(currentTarget.getID());
         break;
      case 852000:
         ba.writeInt(currentTarget.getID());
         break;
      case 852001:
      case 852005:
         MainCanvas.isWaiting = true;
         Map.m_bBattlefield = false;
         if (m_bBattlefieldMenuId != 3) {
            m_bBattlefieldMenuId = 3;
         }

         Map.flagID = new Vector();
         break;
      case 852003:
         ba.writeInt(currentTarget.getID());
         break;
      case 852006:
         MainCanvas.isWaiting = true;
         m_bPlayersortNPC = true;
         break;
      case 852008:
         MainCanvas.isWaiting = true;
         ba.writeInt(currentTarget.getID());
         break;
      case 852016:
      case 852017:
         Player.getInstance().setAttacked(false);
         if (commID == 852016) {
            Player.getInstance().isGettingFlag = 2;
         } else if (commID == 852017) {
            Player.getInstance().isGettingFlag = 3;
         }

         Player.getInstance().setState((byte)3);
         break;
      case 852020:
      case 852022:
         MainCanvas.isWaiting = true;
         break;
      case 852021:
      case 852023:
         MainCanvas.isWaiting = true;
         ba.writeShort(m_sBattlefieldIndex);
         break;
      case 852071:
         MainCanvas.isWaiting = true;
         ba.writeInt(currentTarget.getID());
         break;
      case 852072:
         Player.getInstance().setAttacked(false);
         Player.getInstance().setState((byte)3);
         ba.writeInt(currentTarget.getID());
      }

      return ba.toByteArray();
   }

   public void rec_Parse(int commID, ByteArray data) {
      byte objty = false;
      int oit;
      String on;
      int mmp;
      int ochangehp;
      byte ec;
      byte i;
      byte speC;
      byte yyc;
      byte efc;
      short a0;
      int skillID;
      byte yyc;
      byte objty;
      byte ec;
      byte yyc;
      byte ec;
      byte t;
      switch(commID) {
      case -2147287039:
         objty = data.readByte();
         byte efc;
         switch(objty) {
         case 1:
            data.readByte();
            oit = data.readInt();
            on = data.readUTF();
            String tn1 = data.readUTF();
            byte cp = data.readByte();
            byte p = data.readByte();
            byte pro = data.readByte();
            byte se = data.readByte();
            short le = data.readShort();
            objx = data.readShort();
            objy = data.readShort();
            OtherPlayer otherP = new OtherPlayer(objx, objy, p, pro, se);
            otherP.setCurrentHP(data.readInt());
            otherP.setMAXHP(data.readInt());
            otherP.setCurrentMP(data.readInt());
            otherP.setMAXMP(data.readInt());
            boolean isDead = data.readBoolean();
            otherP.isPVP = data.readBoolean();
            otherP.setID(oit);
            otherP.setRoleName(on);
            otherP.setTitleName(tn1);
            otherP.setPrestige(cp);
            otherP.setPhyle(p);
            otherP.setProfession(pro);
            otherP.setSex(se);
            otherP.setLevel(le);
            boolean isSpecialMonster;
            if (isDead) {
               otherP.setState((byte)9);
               isSpecialMonster = false;
               short weapon;
               if (otherP.getWeaponType() == 4) {
                  weapon = 3;
               } else {
                  weapon = (short)(otherP.nWeapon * 3);
               }

               otherP.nState = 25;
               otherP.motionID = (short)(otherP.nState + 0 + weapon);
            }

            isSpecialMonster = false;

            try {
               isSpecialMonster = data.readBoolean();
            } catch (Exception var68) {
            }

            otherP.setSpecialMonster(isSpecialMonster);
            if (isSpecialMonster) {
               byte[] bodywhere = (byte[])null;
               short[][] pf = (short[][])null;
               byte equiCount = data.readByte();
               pf = new short[equiCount][];
               bodywhere = new byte[equiCount];
               ec = 0;

               while(true) {
                  if (ec >= equiCount) {
                     otherP.changeEquip(bodywhere, pf);
                     break;
                  }

                  bodywhere[ec] = data.readByte();
                  byte yyc = data.readByte();
                  pf[ec] = new short[yyc];

                  for(efc = 0; efc < yyc; ++efc) {
                     pf[ec][efc] = data.readShort();
                  }

                  ++ec;
               }
            }

            addObj(otherP);
            return;
         case 2:
            yyc = data.readByte();
            Monster monster = new Monster(objty, yyc);
            monster.apparel = yyc;
            ochangehp = data.readInt();
            String monsterName = data.readUTF();
            String monstert1 = data.readUTF();
            efc = data.readByte();
            byte np = data.readByte();
            i = data.readByte();
            short mle = data.readShort();
            objx = data.readShort();
            objy = data.readShort();
            monster.x = monster.aimx = objx;
            monster.y = monster.aimy = objy;
            monster.nRow = monster.aimRow = Map.getCurrentRow(monster.y, monster.x);
            monster.nCol = monster.aimCol = Map.getCurrentColumn(monster.y, monster.x);
            monster.setCurrentHP(data.readInt());
            monster.setMAXHP(data.readInt());

            try {
               monster.setDistinction(data.readInt() + 8);
            } catch (Exception var67) {
            }

            monster.setID(ochangehp);
            monster.setRoleName(monsterName);
            monster.setTitleName(monstert1);
            monster.setPrestige(efc);
            monster.setPhyle(np);
            monster.setProfession(i);
            monster.setLevel(mle);
            addObj(monster);
            return;
         case 3:
            speC = data.readByte();
            NPC npc = new NPC(objty, speC);
            npc.apparel = speC;
            int npcID = data.readInt();
            String npcName = data.readUTF();
            String npct1 = data.readUTF();
            ec = data.readByte();
            yyc = data.readByte();
            efc = data.readByte();
            a0 = data.readShort();
            objx = data.readShort();
            objy = data.readShort();
            npc.x = npc.aimx = objx;
            npc.y = npc.aimy = objy;
            npc.nRow = npc.aimRow = Map.getCurrentRow(npc.y, npc.x);
            npc.nCol = npc.aimCol = Map.getCurrentColumn(npc.y, npc.x);
            npc.setCurrentHP(data.readInt());
            npc.setMAXHP(data.readInt());
            byte taskShowType = data.readByte();
            npc.setTaskShowType(taskShowType);
            npc.setID(npcID);
            npc.setRoleName(npcName);
            npc.setTitleName(npct1);
            npc.setPrestige(ec);
            npc.setPhyle(yyc);
            npc.setProfession(efc);
            npc.setLevel(a0);
            npc.setState((byte)0);
            npc.setDirection((byte)1);
            addObj(npc);
            return;
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 10:
         case 11:
            PassiveGO passive = new PassiveGO(objty);
            t = data.readByte();
            int passiveID = data.readInt();
            String passiveName = data.readUTF();
            passive.setImgInd(t);
            passive.setID(passiveID);
            passive.setRoleName(passiveName);
            objx = data.readShort();
            objy = data.readShort();
            passive.x = objx;
            passive.y = objy;
            passive.nRow = Map.getCurrentRow(passive.y, passive.x);
            passive.nCol = Map.getCurrentColumn(passive.y, passive.x);
            addObj(passive);
            if (objty == 10) {
               Map.flagID.addElement(new Integer(passiveID));
            }

            return;
         case 9:
         default:
            return;
         }
      case -2147287038:
         objty = data.readByte();
         switch(objty) {
         case 0:
         case 9:
         default:
            return;
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 10:
         case 11:
            oit = data.readInt();
            if (objty == 10) {
               for(int i = 0; i < Map.flagID.size(); ++i) {
                  if (getObj(oit) == getObj((Integer)Map.flagID.elementAt(i))) {
                     Map.flagID.removeElementAt(i);
                  }
               }
            }

            getInstance();
            delObj(oit);
            return;
         }
      case -2147287037:
         objty = data.readByte();
         oit = data.readInt();
         on = data.readUTF();
         byte shengwang = data.readByte();
         objx = data.readShort();
         objy = data.readShort();
         int chp = data.readInt();
         int mhp = data.readInt();
         ActiveGO motherP = null;
         if (getObj(oit) instanceof ActiveGO) {
            motherP = (ActiveGO)getObj(oit);
         }

         if (motherP != null) {
            motherP.setRoleName(on);
            motherP.setPrestige(shengwang);
            motherP.setCurrentHP(chp);
            motherP.setMAXHP(mhp);
            motherP.setAimMapXY(objx, objy);
            boolean isMove = motherP.setAimRowColFindPath(Map.getCurrentRow(objy, objx), Map.getCurrentColumn(objy, objx));
            if (isMove && motherP.getState() != 9) {
               motherP.setMoveState();
            }

            if (currentTarget == motherP) {
               Player.getInstance().changeTargetRound();
            }

            switch(objty) {
            case 1:
               int cmp = data.readInt();
               mmp = data.readInt();
               String tnv = data.readUTF();
               motherP.setCurrentMP(cmp);
               motherP.setMAXMP(mmp);
               motherP.setTitleName(tnv);

               try {
                  ((OtherPlayer)motherP).isPVP = data.readBoolean();
               } catch (Exception var66) {
               }

               return;
            case 2:
            default:
               return;
            case 3:
               ec = data.readByte();
               motherP.setTaskShowType(ec);
            }
         }
         break;
      case -2147287036:
         removeAll();
         objx = data.readShort();
         objy = data.readShort();
         Player.getInstance().x = Player.getInstance().aimx = objx;
         Player.getInstance().y = Player.getInstance().aimy = objy;
         Player.getInstance().nRow = Player.getInstance().aimRow = Map.getCurrentRow(Player.getInstance().y, Player.getInstance().x);
         Player.getInstance().nCol = Player.getInstance().aimCol = Map.getCurrentColumn(Player.getInstance().y, Player.getInstance().x);
         Player.getInstance().setState((byte)0);
         break;
      case -2147287035:
         byte[] bodywhere = (byte[])null;
         short[][] pf = (short[][])null;
         mmp = data.readInt();
         OtherPlayer otherP = (OtherPlayer)getObj(mmp);
         if (otherP != null) {
            objty = data.readByte();
            pf = new short[objty][];
            bodywhere = new byte[objty];

            for(ec = 0; ec < objty; ++ec) {
               data.readByte();
               bodywhere[ec] = data.readByte();
               byte eft;
               if (bodywhere[ec] > 0) {
                  yyc = data.readByte();
                  pf[ec] = new short[yyc];

                  for(eft = 0; eft < yyc; ++eft) {
                     pf[ec][eft] = data.readShort();
                  }
               } else if (bodywhere[ec] < 0) {
                  yyc = (byte)ActiveGO.ORIEquipment[Math.abs(bodywhere[ec]) - 1 + otherP.getSex() * (ActiveGO.ORIEquipment.length / 2)].length;
                  pf[ec] = new short[yyc];

                  for(eft = 0; eft < yyc; ++eft) {
                     pf[ec][eft] = ActiveGO.ORIEquipment[Math.abs(bodywhere[ec]) - 1 + otherP.getSex() * (ActiveGO.ORIEquipment.length / 2)][eft];
                  }
               }
            }

            ec = data.readByte();
            if (ec != 4) {
               otherP.setWeaponType(ec, false);
            } else {
               otherP.setWeaponType(ec, true);
            }

            otherP.changeEquip(bodywhere, pf);
         }
         break;
      case -2147287033:
         int hp_oit = data.readInt();
         int ocurrenthp = data.readInt();
         int omaxhp = data.readInt();
         ochangehp = data.readInt();
         ec = data.readByte();
         ActiveGO otherP_HP = null;
         if (getObj(hp_oit) instanceof ActiveGO) {
            otherP_HP = (ActiveGO)getObj(hp_oit);
         }

         if (otherP_HP != null) {
            otherP_HP.setCurrentHP(ocurrenthp);
            otherP_HP.setMAXHP(omaxhp);
            int[] th = new int[3];
            if ((ec & 128) != 0) {
               th[0] = 1;
            } else {
               th[0] = 0;
            }

            th[1] = 0;
            th[2] = ochangehp;
            otherP_HP.saveHPChange(th);
            ec = (byte)(ec & 127);
            otherP_HP.setChanHPType(ec);
            if (hp_oit == Player.getInstance().getID() && th[2] < 0) {
               Player.getInstance().setAttacked(true);
            }
         }
         break;
      case -2147287032:
         int die_oit = data.readInt();
         if (die_oit == Player.getInstance().getID()) {
            Player.getInstance().isInvisible = Player.getInstance().isFreezing = false;
            if (Player.getInstance().getState() != 9) {
               Player.getInstance().nType = 0;
               Player.getInstance().bStealth = false;
               Player.getInstance().setState((byte)9);
               Player.isAttacking = false;
            }
         } else {
            ActiveGO otherP_DIE = (ActiveGO)getObj(die_oit);
            if (otherP_DIE != null) {
               otherP_DIE.isInvisible = otherP_DIE.isFreezing = false;
               otherP_DIE.delSpecial(otherP_DIE.getID());
               if (otherP_DIE.getState() != 9) {
                  otherP_DIE.setState((byte)9);
                  if (currentTarget == otherP_DIE) {
                     Player.getInstance();
                     if (Player.skilltimer[0] == 0) {
                        Player.isAttacking = false;
                     }

                     if (Player.getInstance().getState() != 2) {
                        if (Player.getInstance().getState() != 9) {
                           Player.getInstance().setState((byte)0);
                        }

                        currentTarget = Player.getInstance();
                        selectCircle.setSpecial(currentTarget, (short)0, (byte)-1);
                     }
                  }
               }
            }
         }
         break;
      case -2147287031:
         byte sp_idn0 = data.readByte();
         short sp_x = data.readShort();
         short var107 = data.readShort();
         break;
      case -2147287030:
         byte sp_idn1 = data.readByte();
         int special_oit = data.readInt();
         ActiveGO otherP_SPECIAL = (ActiveGO)getObj(special_oit);
         if (otherP_SPECIAL != null) {
            otherP_SPECIAL.addSpecial(otherP_SPECIAL, sp_idn1, (byte)1, (byte)2);
         }
         break;
      case -2147287029:
         int att_oit = data.readInt();
         boolean isSkill = data.readBoolean();
         ActiveGO otherP_ATT = (ActiveGO)getObj(att_oit);
         if (otherP_ATT != null) {
            if (isSkill) {
               otherP_ATT.setState((byte)4);
            } else {
               otherP_ATT.setState((byte)2);
            }

            otherP_ATT.setNextFrame((byte)0);
         }
         break;
      case -2147287028:
         a0 = data.readShort();
         if (Player.getInstance().getLevel() != 0 && a0 > Player.getInstance().getLevel()) {
            Player.getInstance().m_bUpgrade = true;
         }

         Player.getInstance().setLevel(a0);
         Player.getInstance().setCurrentHP(data.readInt());
         Player.getInstance().setMAXHP(data.readInt());
         Player.getInstance().setCurrentMP(data.readInt());
         Player.getInstance().setMAXMP(data.readInt());
         Player.getInstance().setCurrentEXP(data.readLong());
         Player.getInstance().setMAXEXP(data.readLong());
         Player.getInstance().setSkillLimit(data.readByte());
         Player.getInstance().isFight = data.readBoolean();
         break;
      case -2147287026:
         int buff_oit = data.readInt();
         ActiveGO otherP_BUFF = (ActiveGO)getObj(buff_oit);
         if (otherP_BUFF != null) {
            for(i = 0; i < 12; ++i) {
               otherP_BUFF.buffTYPE[i] = data.readByte();
            }

            short buff_speed = data.readShort();
            boolean buff_notmove = data.readBoolean();
            otherP_BUFF.isnotattck = data.readBoolean();
            otherP_BUFF.isnotcast = data.readBoolean();
            if (buff_oit == Player.getInstance().getID() && (otherP_BUFF.isnotattck || otherP_BUFF.isnotcast || buff_notmove)) {
               Player.getInstance().setAttacked(true);
            }

            speC = data.readByte();
            otherP_BUFF.isInvisible = otherP_BUFF.isFreezing = false;
            otherP_BUFF.delSpecial(otherP_BUFF.getID());
            otherP_BUFF.nType = 0;
            otherP_BUFF.bStealth = false;

            for(byte i = 0; i < speC; ++i) {
               short speID = data.readShort();
               if (speID != 79 && speID != 80 && speID != 81) {
                  if (speID == 13) {
                     otherP_BUFF.isFreezing = true;
                     otherP_BUFF.addSpecial(otherP_BUFF, speID, (byte)-2, (byte)1);
                  } else if (speID != -1 && speID != -2 && speID != -3 && speID != -4) {
                     if (speID == -5) {
                        otherP_BUFF.nType = 0;
                        otherP_BUFF.bStealth = true;
                     }
                  } else {
                     otherP_BUFF.nType = (byte)(-speID);
                     otherP_BUFF.bStealth = false;
                     if (otherP_BUFF.getState() != 3) {
                        otherP_BUFF.setState((byte)0);
                     }
                  }
               } else {
                  otherP_BUFF.isInvisible = true;
                  otherP_BUFF.addSpecial(otherP_BUFF, speID, (byte)-2, (byte)0);
                  if (buff_oit == Player.getInstance().getID()) {
                     Player.getInstance().setAttacked(true);
                  }
               }
            }

            if (buff_speed == 200) {
               otherP_BUFF.curStep = 18;
            } else if (buff_speed == 100) {
               otherP_BUFF.curStep = 49;
            } else if (buff_speed > 100) {
               otherP_BUFF.curStep = 58;
            } else if (buff_speed < 100) {
               otherP_BUFF.curStep = 43;
            }

            if (buff_notmove) {
               otherP_BUFF.curStep = 27;
            }

            boolean isOnRide = data.readBoolean();
            if (isOnRide) {
               byte datalen = 0;
               byte old_horseState = otherP_BUFF.horseState;
               otherP_BUFF.horseState = data.readByte();
               short[][] pf_horse;
               if (otherP_BUFF.horseState == 1) {
                  if (old_horseState == 0) {
                     otherP_BUFF.CURRRider = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}};
                  } else if (old_horseState == 1 && otherP_BUFF.cartPlayer != null) {
                     pf_horse = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}, {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                     otherP_BUFF.changeRide((byte)13, pf_horse, false);
                     short[][] temp = new short[][]{otherP_BUFF.CURRRider[0], otherP_BUFF.CURRRider[1], otherP_BUFF.CURRRider[2], otherP_BUFF.CURRRider[3], otherP_BUFF.CURRRider[4], otherP_BUFF.CURRRider[5]};
                     otherP_BUFF.CURRRider = temp;
                  }

                  datalen = 6;
                  otherP_BUFF.getInHorse();
               } else if (otherP_BUFF.horseState == 2) {
                  if (otherP_BUFF.cartPlayer == null) {
                     if (old_horseState == 0) {
                        otherP_BUFF.CURRRider = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}, {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                     } else if (old_horseState == 1) {
                        pf_horse = new short[][]{otherP_BUFF.CURRRider[0], otherP_BUFF.CURRRider[1], otherP_BUFF.CURRRider[2], otherP_BUFF.CURRRider[3], otherP_BUFF.CURRRider[4], otherP_BUFF.CURRRider[5], {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                        otherP_BUFF.CURRRider = pf_horse;
                     }
                  }

                  datalen = 13;
                  otherP_BUFF.getInCart();
               }

               pf_horse = (short[][])null;
               objty = data.readByte();
               pf_horse = new short[objty][];

               for(ec = 0; ec < objty; ++ec) {
                  data.readByte();
                  yyc = data.readByte();
                  pf_horse[ec] = new short[yyc];

                  for(efc = 0; efc < yyc; ++efc) {
                     pf_horse[ec][efc] = data.readShort();
                  }
               }

               otherP_BUFF.changeRide(datalen, pf_horse, true);
            } else if (otherP_BUFF.horseState != 0) {
               otherP_BUFF.dismount();
            }
         }
         break;
      case -2147287024:
         data.readByte();
         data.readByte();
         int tid = data.readInt();
         Player.getInstance().setID(tid);
         String abc = data.readUTF();
         String tn = data.readUTF();
         Player.getInstance().setRoleName(abc);
         Player.getInstance().setTitleName(tn);
         byte a = data.readByte();
         Player.getInstance().setCamp(a);
         byte b = data.readByte();
         Player.getInstance().setPhyle(b);
         byte c = data.readByte();
         Player.getInstance().setProfession(c);
         byte s = data.readByte();
         Player.getInstance().setSex(s);
         int lid = data.readInt();
         Player.getInstance().setLoverState(data.readByte());
         Player.getInstance().setLoverName(data.readUTF());
         Player.getInstance().setLoverId(lid);
         int clanId = data.readInt();
         Player.getInstance().setClanId(clanId);
         byte clanRight = data.readByte();
         Player.getInstance().setClanRight(clanRight);
         int kingId = data.readInt();
         Player.getInstance().setKingId(kingId);
         byte kingRight = data.readByte();
         Player.getInstance().setKingRight(kingRight);
         Player.getInstance().setPrestige((byte)4);
         objx = data.readShort();
         objy = data.readShort();
         Player.getInstance().setCurrentHP(data.readInt());
         Player.getInstance().setMAXHP(data.readInt());
         Player.getInstance().x = Player.getInstance().aimx = objx;
         Player.getInstance().y = Player.getInstance().aimy = objy;
         Player.getInstance().nRow = Player.getInstance().aimRow = Map.getCurrentRow(Player.getInstance().y, Player.getInstance().x);
         Player.getInstance().nCol = Player.getInstance().aimCol = Map.getCurrentColumn(Player.getInstance().y, Player.getInstance().x);
         Player.getInstance().copy_CR();
         Player.getInstance().isSendMove = true;
         Player.getInstance().load_OrgEquip(Player.getInstance().getSex());
         byte npcxxxx = Player.getInstance().getNPCFileName(Player.getInstance().getPhyle(), Player.getInstance().getSex());
         Player.getInstance().load_humanIMG(npcxxxx);
         Player.getInstance().setState((byte)0);
         Player.getInstance().setDirection((byte)1);
         long[] skda = new long[4];

         for(byte il = 0; il < 4; ++il) {
            skda[il] = data.readLong();
         }

         boolean isPlayerDead = data.readBoolean();
         Player.getInstance().isPVP = data.readBoolean();
         if (isPlayerDead) {
            Player.getInstance().setState((byte)9);
            short weapon = false;
            short weapon;
            if (Player.getInstance().getWeaponType() == 4) {
               weapon = 3;
            } else {
               weapon = (short)(Player.getInstance().nWeapon * 3);
            }

            Player var10000 = Player.getInstance();
            Player.getInstance();
            var10000.nState = 25;
            Player.getInstance().motionID = (short)(Player.getInstance().nState + 0 + weapon);
         } else {
            UITopForm.removeCurTopForm();
         }

         if (MainCanvas.m_bTourist == 1) {
            MainCanvas.mc.initSave(abc);
            Player.loadNPC_SKILLData(Player.getInstance().getProfession(), data);
         }

         Player.getInstance().checkCanUseSkill(skda);
         addObj(Player.getInstance());
         Runnable r = new Runnable() {
            public void run() {
               while(!MainCanvas.isLoadComplete) {
               }

               MainCanvas.setState((byte)5);
               MainCanvas.endLoadingProgress();
               MainCanvas.loadCount = 0;
               GOManager.selectCircle = new Special(Player.getInstance(), (short)0, (byte)-1, (byte)2);
               MainCanvas.ni.send(196637);
            }
         };
         Thread thread = new Thread(r);
         thread.start();
         break;
      case -2147287023:
         Player.getInstance();
         Player.skillCD[0] = data.readInt();
         break;
      case -2147287019:
         int ride_oit = data.readInt();
         OtherPlayer otherP_RIDE = (OtherPlayer)getObj(ride_oit);
         if (otherP_RIDE != null) {
            t = data.readByte();
            if (t == 1) {
               otherP_RIDE.nType = 0;
               otherP_RIDE.bStealth = false;
               byte datalen = 0;
               byte old_horseState = otherP_RIDE.horseState;
               otherP_RIDE.horseState = data.readByte();
               short[][] pf_horse;
               if (otherP_RIDE.horseState == 1) {
                  if (old_horseState == 0) {
                     otherP_RIDE.CURRRider = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}};
                  } else if (old_horseState == 1 && otherP_RIDE.cartPlayer != null) {
                     pf_horse = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}, {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                     otherP_RIDE.changeRide((byte)13, pf_horse, false);
                     short[][] temp = new short[][]{otherP_RIDE.CURRRider[0], otherP_RIDE.CURRRider[1], otherP_RIDE.CURRRider[2], otherP_RIDE.CURRRider[3], otherP_RIDE.CURRRider[4], otherP_RIDE.CURRRider[5]};
                     otherP_RIDE.CURRRider = temp;
                  }

                  datalen = 6;
                  otherP_RIDE.getInHorse();
               } else if (otherP_RIDE.horseState == 2) {
                  if (otherP_RIDE.cartPlayer == null) {
                     if (old_horseState == 0) {
                        otherP_RIDE.CURRRider = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}, {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                     } else if (old_horseState == 1) {
                        pf_horse = new short[][]{otherP_RIDE.CURRRider[0], otherP_RIDE.CURRRider[1], otherP_RIDE.CURRRider[2], otherP_RIDE.CURRRider[3], otherP_RIDE.CURRRider[4], otherP_RIDE.CURRRider[5], {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
                        otherP_RIDE.CURRRider = pf_horse;
                     }
                  }

                  datalen = 13;
                  otherP_RIDE.getInCart();
               }

               pf_horse = (short[][])null;
               objty = data.readByte();
               pf_horse = new short[objty][];

               for(byte ec = 0; ec < objty; ++ec) {
                  data.readByte();
                  yyc = data.readByte();
                  pf_horse[ec] = new short[yyc];

                  for(byte efc = 0; efc < yyc; ++efc) {
                     pf_horse[ec][efc] = data.readShort();
                  }
               }

               otherP_RIDE.changeRide(datalen, pf_horse, true);
            } else if (t == 0) {
               otherP_RIDE.horseState = data.readByte();
               if (otherP_RIDE.CURRRider != null) {
                  if (otherP_RIDE.horseState == 1) {
                     otherP_RIDE.horseState = 0;
                     otherP_RIDE.releaseHorse();
                     otherP_RIDE.CURRRider = null;
                  } else if (otherP_RIDE.horseState == 2) {
                     otherP_RIDE.horseState = 0;
                     otherP_RIDE.releaseCart();
                     otherP_RIDE.CURRRider = null;
                  }
               } else {
                  otherP_RIDE.horseState = 0;
               }
            }
         }
         break;
      case -2147287018:
         Player.getInstance().isCollection = false;
         break;
      case -2147287015:
         String skill = data.readUTF();
         if (SIManager.shortCut == null) {
            SIManager.shortCut = new SIShortCut();
         }

         SIManager.shortCut.curShortCutGroup = 0;
         if (skill.length() == 0) {
            SIManager.getInstance();
            SIManager.shortCut.initShortCut();
         } else {
            SIManager.shortCut.deShortCutStr(skill);
         }
         break;
      case -2147287014:
         int length = data.readByte();

         for(skillID = 0; skillID < length; ++skillID) {
            SIManager.shortCut.deGoods(data.readInt(), data.readShort(), data.readUTF(), data.readByte(), data.readInt());
         }

         SIManager.shortCut.refreshShortCutAll();
         break;
      case -2147287012:
         skillID = data.readInt();
         SIShortCut sc = SIManager.shortCut;
         int index = Player.getInstance().getIND_FromSkillData(skillID);
         if (index != -1) {
            Player.getInstance();
            Player.CD_Count[index] = 0L;
            sc.setCD((byte)1, skillID, false);
            sc.refreshShortCutAll();
         }
         break;
      case -2147287009:
         byte fcp = data.readByte();
         this.reciPingTime = System.currentTimeMillis();
         break;
      case -2147287005:
         Player.getInstance().setTreasurePicIDMay(data.readInt());
         break;
      case -2147287004:
         int id = data.readInt();
         OtherPlayer other = (OtherPlayer)getObj(id);
         int picID = data.readInt();
         if (other == null) {
            return;
         }

         other.setTreasurePicIDMay(picID);
         break;
      case -2147155967:
         MainCanvas.uiData = data.readByteArray(data.getLength() - 6);
         break;
      case -2147090433:
         MainCanvas.userData = data.readByteArray(data.getLength() - 6);
         MainCanvas.isWaiting = false;
         break;
      case -2146631679:
         yyc = data.readByte();
         boolean haveMapMenu = false;
         int[] finds = new int[yyc];
         String[] fcens = new String[yyc];

         for(int fi = 0; fi < yyc; ++fi) {
            finds[fi] = data.readInt();
            haveMapMenu = true;
            fcens[fi] = data.readUTF();
         }

         if (haveMapMenu) {
            UIMenu.npcMenu = new UIMenu((byte)1);
            UIMenu.npcMenu.addMenu(fcens, finds);
            MainCanvas.setGameState((byte)5);
            MainCanvas.isWaiting = false;
         } else {
            MainCanvas.isWaiting = false;
         }
         break;
      case -2146631644:
         FlagGain = data.readInt();
         if (FlagGain == 0) {
            return;
         }

         OtherPlayer otherplay = (OtherPlayer)getObj(FlagGain);
         otherplay.m_bGainedFlag = data.readBoolean();
         otherplay.m_bFlagLind = data.readByte();
         if (otherplay.m_bFlagLind == 1) {
            Map.m_nBannerID[0] = FlagGain;
         } else if (otherplay.m_bFlagLind == 2) {
            Map.m_nBannerID[1] = FlagGain;
         }
         break;
      case -2146631624:
         Map.m_bBattlefield = false;
         if (m_bBattlefieldMenuId != 3) {
            m_bBattlefieldMenuId = 3;
         }

         Map.flagID = new Vector();
         if (MainCanvas.curTopForm != null) {
            UITopForm.removeAllTopForm();
         }
      }

   }
}
