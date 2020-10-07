package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.PalvelupisteDAO.IPalvelupisteDAO;

public class PalvelupisteAccessObject implements IPalvelupisteDAO {
	
	private static SessionFactory sessionfactory;

	public PalvelupisteAccessObject() {
		sessionfactory = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			sessionfactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Virhe!");
			StandardServiceRegistryBuilder.destroy(registry);
			System.exit(-1);
		}
	}

	@Override
	public boolean createPalvelupiste(Palvelupiste palvelupiste) {
		
		Transaction transaction = null;
		try (Session istunto = sessionfactory.openSession()) {
			if (palvelupiste != null) {
				transaction = istunto.beginTransaction();
				istunto.saveOrUpdate(palvelupiste);
				transaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.err.println("Virhe!");
		}

		return false;

	}

	@Override
	public Palvelupiste readPalvelupiste(int id) {

		Transaction transaction = null;
		try (Session session = sessionfactory.openSession()) {
			transaction = session.beginTransaction();
			Palvelupiste palvelupiste = new Palvelupiste();
			session.load(palvelupiste, id);

			session.getTransaction().commit();

			return palvelupiste;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return null;
		}
	}

	@Override
	public Palvelupiste[] readpalvelupisteet() {
		
		Transaction transaction = null;
		Palvelupiste[] ppisteet;
		int i = 0;
		try (Session session = sessionfactory.openSession()) {
			transaction = session.beginTransaction();
			List<Palvelupiste> ppList = session.createQuery("from  tapahtumat").getResultList();
			ppisteet = new Palvelupiste[ppList.size()];

			for(Palvelupiste p : ppList) {
				ppisteet[i] = p;
				i++;
			}

		} catch (Exception e) {
			System.err.println("Virhe!");
			ppisteet = null;
		}
		return ppisteet;
	}

	@Override
	public boolean updateValuutta(Palvelupiste palvelupiste) {
		try (Session session = sessionfactory.openSession()) {

			TapahtumanTyyppi palvelutyyppi = palvelupiste.getPalvelutyyppi();
			int asiakkaidenlkm = palvelupiste.getAsiakkaidenmaara();
			long palveluaikaB = palvelupiste.getB();
			

			session.beginTransaction();

			palvelupiste = (Palvelupiste)session.get(Palvelupiste.class, palvelupiste.getId());

			if (palvelupiste != null) {
				palvelupiste.setPalvelutyyppi(palvelutyyppi);
				palvelupiste.setAsiakkaidenlkm(asiakkaidenlkm);
				palvelupiste.setPalveluaikaB(palveluaikaB);
			} else {
				System.out.println("Päivitys epäonnistui.");
				return false;
			}

			session.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}

	@Override
	public boolean deleteValuutta(int id) {
		Palvelupiste palvelupiste= readPalvelupiste(id);
		
		try (Session session = sessionfactory.openSession()) {
			
			session.beginTransaction();
			
			if (palvelupiste != null) {
				session.delete(palvelupiste);
			} else {
				return false;
			}

			session.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}


}

